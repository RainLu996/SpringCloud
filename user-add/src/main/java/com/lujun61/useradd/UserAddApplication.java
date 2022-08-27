package com.lujun61.useradd;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.lujun61.useradd.dao")
@EnableMPP
public class UserAddApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAddApplication.class, args);
    }

}
