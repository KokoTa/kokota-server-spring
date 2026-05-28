package com.example.kokotaserver.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kokotaserver.common.ResponseCode;
import com.example.kokotaserver.exception.BusinessException;
import com.example.kokotaserver.service.IUserService;

@SpringBootTest
public class IUserServiceTest {
  @Autowired
  IUserService userService;

  @Test
  void 账户为空() {
    try {
      userService.registerUser("", "12345678", "12345678");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }

  @Test
  void 账户长度不足() {
    try {
      userService.registerUser("Ko", "12345678", "12345678");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }

  @Test
  void 密码为空() {
    try {
      userService.registerUser("KokoTa", "", "12345678");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }

  @Test
  void 密码长度不足() {
    try {
      userService.registerUser("KokoTa", "123", "123");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }

  @Test
  void 账户含特殊字符() {
    try {
      userService.registerUser("KokoTa!", "12345678", "12345678");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }

  @Test
  void 两次密码不一致() {
    try {
      userService.registerUser("KokoTa", "12345678", "12345677");
    } catch (BusinessException e) {
      Assertions.assertEquals(ResponseCode.PARAMS_ERROR.getCode(), e.getCode());
    }
  }
}