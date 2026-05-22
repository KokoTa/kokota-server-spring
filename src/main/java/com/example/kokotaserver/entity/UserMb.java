package com.example.kokotaserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Mybatis Plus 测试表
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-22
 */
@Data
@Builder
@TableName("user_mb")
public class UserMb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;
}
