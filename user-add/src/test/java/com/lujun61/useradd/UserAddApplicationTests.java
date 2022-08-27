package com.lujun61.useradd;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lujun61.useradd.dao.ProductMapper;
import com.lujun61.useradd.dao.UserMapper;
import com.lujun61.useradd.entity.Product;
import com.lujun61.useradd.entity.User;
import com.lujun61.useradd.enums.SexEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jun Lu
 * @description MyBatisPlus测试类
 * @date 2022-08-25 22:51:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserAddApplication.class})
class UserAddApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    ProductMapper productMapper;

    @Test
    void testSelect() {

        // selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setUId(1L);
        user.setName("陆俊");
        // 与tkMapper不同的是，这里的update方法都是按照指定值更新，而不会将其他未设置新值的数据更新
        userMapper.updateById(user);
    }

    @Test
    void testInsert() {
        User user = new User();
        //user.setUId(666L);   // 指定id，MBP将不会自动生成id
        //user.setName("RainLu");
        //userMapper.insert(user);

        user = new User();
        user.setName("RainLu");   // 不指定id，MBP会自动生成id
        userMapper.insert(user);
    }

    @Test
    void testWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(con -> con.like("name", "lu"));   // lambda优先
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
    }

    @Test
    public void testPage() {
        //设置分页参数
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    @Test
    public void testCustomPage() {
        //设置分页参数
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPageVoWhereGtAge(page, 20);

        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    // 未配置乐观锁
    @Test
    public void testConcurrentUpdate() {
        /* 模拟小王小李同时查询出相同的数据 */
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());

        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        // 小王修改的结果将会覆盖小李修改的结果
        System.out.println("小王修改结果：" + result2);

        //最后的结果
        Product p3 = productMapper.selectById(1L);

        //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());
    }

    // 配置乐观锁
    @Test
    public void testConcurrentVersionUpdate() {
        //小李取数据
        Product p1 = productMapper.selectById(1L);
        //小王取数据
        Product p2 = productMapper.selectById(1L);
        //小李修改 + 50
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改的结果：" + result1);
        //小王修改 - 30
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改的结果：" + result2);
        if (result2 == 0) {
            //失败重试，重新获取version并更新
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result2 = productMapper.updateById(p2);
        }
        System.out.println("小王修改重试的结果：" + result2);
        //老板看价格
        Product p3 = productMapper.selectById(1L);
        System.out.println("老板看价格：" + p3.getPrice());
    }

    @Test
    public void testSexEnum() {
        User user = new User();
        user.setName("Enum");
        user.setAge(20);

        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);

        //INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
        //Parameters：Enum(String), 20(Integer), 1(Integer)
        userMapper.insert(user);
    }
}
