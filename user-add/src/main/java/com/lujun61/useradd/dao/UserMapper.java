package com.lujun61.useradd.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lujun61.useradd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据年龄查询用户列表，分页显示
     * @param page 分页对象，xml中可以自动从里面进行取值，而不需要人为获取封装值。传递参数 Page 即自动分页，必须放在第一位
     * @param age 年龄
     */
    Page<User> selectPageVoWhereGtAge(@Param("page") Page<User> page, @Param("age") Integer age);

}
