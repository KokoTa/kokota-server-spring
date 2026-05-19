package com.example.kokotaserver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * 文档地址: https://baomidou.com/guides/new-code-generator/
 * 生成 conroller、entity、mapper、xml、service、serviceImpl
 */
public class CodeGenerator {
  public static void main(String[] args) {
    FastAutoGenerator.create("jdbc:mysql://localhost:3306/yupi", "root", "123123")
        // 全局配置
        .globalConfig(builder -> builder.outputDir(System.getProperty("user.dir") + "/src/main/java").author("KokoTa"))
        // 包名配置
        .packageConfig((scanner, builder) -> builder.parent("com.example.kokotaserver"))
        // 策略配置
        .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
            .entityBuilder()
            .enableLombok()
            .build())
        // 使用Velocity引擎模板，默认的是Velocity引擎模板
        .templateEngine(new VelocityTemplateEngine())
        .execute();
  }

  // 处理 all 情况
  protected static List<String> getTables(String tables) {
    return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
  }
}
