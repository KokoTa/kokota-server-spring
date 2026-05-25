package com.example.kokotaserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.entity.request.RequestUser;
import com.example.kokotaserver.service.IUserService;

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
  private IUserService userService;

  @PostMapping("/register")
  public Long register(@RequestBody @Valid RequestUser requestUser) {
    return userService.registerUser(requestUser.getUserAccount(), requestUser.getUserPassword(),
        requestUser.getCheckPassword());
  }

  @PostMapping("/login")
  public User login(@RequestBody @Valid RequestUser requestUser, HttpServletRequest request) {
    return userService.login(requestUser.getUserAccount(), requestUser.getUserPassword(), request);
  }

}
