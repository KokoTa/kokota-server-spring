package com.example.kokotaserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.kokotaserver.constant.UserConstant;
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

  @GetMapping("/search")
  public List<User> searchUsers(@RequestParam String userAccount, HttpServletRequest request) {
    if (!isAdmin(request)) {
      return new ArrayList<>();
    }

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (userAccount != null) {
      queryWrapper.like("userAccount", userAccount);
    }
    List<User> userList = userService.list(queryWrapper).stream().map(user -> userService.safeUser(user))
        .collect(Collectors.toList());
    return userList;
  }

  @PostMapping("/delete")
  public Boolean deleteUsers(@RequestBody List<Long> ids, HttpServletRequest request) {
    if (!isAdmin(request)) {
      return false;
    }
    if (ids == null || ids.isEmpty()) {
      return false;
    }
    return userService.removeByIds(ids);
  }

  private boolean isAdmin(HttpServletRequest request) {
    Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
    User user = (User) userObj;
    return user != null && user.getRoleStatus() == UserConstant.ADMIN_ROLE_STATUS;
  }
}
