package com.lujun61.useradd.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lujun61.useradd.enums.SexEnum;
import lombok.Data;

@Data
@TableName("t_user")        /* 设置当前实体类所对应的表名 */
public class User {

    // MyBatisPlus会将实体类属性“id”对应的【字段】视为主键，并基于雪花算法生成id
    /* 将属性所对应的表中字段指定为主键。
           value属性指定表中字段名。
           type属性：
              IdType.ASSIGN_ID：基于雪花算法的策略生成数据id，与数据库id是否设置自增无关。
              IdType.AUTO：使用数据库的自增策略，注意，该类型请确保数据库设置了id自增，否则无效
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long uId;

    @TableField("user_name")   /* 指定实体类属性所对应表中的字段名 */
    private String name;
    private Integer age;
    private String email;

    private SexEnum sex;

    @TableLogic        /* 指定逻辑删除字段：在使用delete语句的时候，实际上是使用update语句将is_delete设置为1。
                          当select时，不去查询is_delete为1的数据
                       */
    private Integer is_delete;
}
