package com.example.kokotaserver.entity.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 为什么需要继承 Serializable 接口？因为在网络传输中，请求体需要被序列化和反序列化。
 * 实现 Serializable 接口可以确保对象能够被正确地序列化和反序列化，从而保证数据的完整性和一致性。
 * 不写的后果是什么？不写的话默认序列化会根据字段动态生成 serialVersionUID。
 * 比如你往 Redis 里存了一个 UserDTO，第二天你在 UserDTO 里加了一个 phoneNumber 字段，
 * 然后去 Redis 里取之前存的对象。此时 JVM 会发现“我类上的ID是新的（因为加了字段），但数据里的ID是老的，不一致！”
 * 于是它就会抛出 InvalidClassException 异常，数据反序列化失败。
 */
@Data
public class RequestUser implements Serializable {

  private static final long serialVersionUID = -9339368913L;

  @NotBlank
  private String userAccount;

  @NotBlank
  private String userPassword;

  private String checkPassword;

}
