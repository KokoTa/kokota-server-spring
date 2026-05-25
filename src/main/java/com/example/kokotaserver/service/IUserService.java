package com.example.kokotaserver.service;

import com.example.kokotaserver.entity.User;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-19
 */
public interface IUserService extends IService<User> {

  /**
   * 用户注册
   *
   * @param userAccount   用户账户
   * @param userPassword  用户密码
   * @param checkPassword 确认密码
   * @return 注册成功返回用户 id，注册失败返回 -1
   */
  long registerUser(String userAccount, String userPassword, String checkPassword);

  /**
   * 用户登录
   *
   * @param userAccount  用户账户
   * @param userPassword 用户密码
   * @return 登录成功返回用户信息，登录失败返回 null
   */
  User login(String userAccount, String userPassword, HttpServletRequest request);

  /**
   * 获取当前登录用户的信息
   * @param request
   * @return 当前登录用户的信息
   */
  User currentUser(HttpServletRequest request);

  /**
   * 用户脱敏
   *
   * @param user 用户信息
   * @return 脱敏后的用户信息
   */
  User safeUser(User user);

}
