package com.lujun61.useradd;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author Jun Lu
 * @description MyBatisPlus代码生成器
 * @date 2022-08-26 22:27:41
 */
public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/springcloud??useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456")
                        .globalConfig(builder -> {
                            builder.author("rainlu") // 设置作者
                                    //.enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("E://mybatis_plus"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com.lujun61") // 设置父包名
                                    .moduleName("mybatisplus") // 设置父包模块名
                                    // 设置mapperXml生成路径
                                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://mybatis_plus"));
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("t_user") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }

}
