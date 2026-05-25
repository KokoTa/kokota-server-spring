package com.example.kokotaserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-22
 */
@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0 - 正常 1 - 禁用
     */
    private Integer userStatus;

    /**
     * 角色 0 - 普通用户 1 - 管理员
     */
    private Integer roleStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
}
