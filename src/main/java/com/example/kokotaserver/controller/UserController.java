package com.example.kokotaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.service.impl.UserServiceImpl;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @PostMapping("/register")
  public long getMethodName() {
    return userService.registerUser("KokoTa", "12345678", "12345678");
  }

  @PostMapping("/login")
  public User login() {
    return userService.login("KokoTa", "12345678");
  }

}
