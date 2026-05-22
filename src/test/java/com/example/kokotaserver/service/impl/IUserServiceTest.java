package com.example.kokotaserver.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kokotaserver.service.IUserService;

@SpringBootTest
public class IUserServiceTest {
  @Autowired
  IUserService userService;

  @Test
  void 账户为空() {
    long result = userService.registerUser("", "12345678", "12345678");
    Assertions.assertEquals(-1, result);
  }

  @Test
  void 账户长度不足() {
    long result = userService.registerUser("Ko", "12345678", "12345678");
    Assertions.assertEquals(-1, result);
  }

  @Test
  void 密码为空() {
    long result = userService.registerUser("KokoTa", "", "12345678");
    Assertions.assertEquals(-1, result);
  }

  @Test
  void 密码长度不足() {
    long result = userService.registerUser("KokoTa", "123", "123");
    Assertions.assertEquals(-1, result);
  }

  @Test
  void 账户含特殊字符() {
    long result = userService.registerUser("KokoTa!", "12345678", "12345678");
    Assertions.assertEquals(-1, result);
  }

  @Test
  void 两次密码不一致() {
    long result = userService.registerUser("KokoTa", "12345678", "12345677");
    Assertions.assertEquals(-1, result);
  }
}
