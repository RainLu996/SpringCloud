package com.lujun61.useradd.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;


/**
 * @description 如果表中的有些字段值是固定的，例如性别（男或女）；此时我们可以使用MyBatis-Plus的通用枚举来实现
 * @author Jun Lu
 * @date 2022-08-26 22:02:20
 */
@Getter
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
