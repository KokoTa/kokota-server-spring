package com.example.kokotaserver.service.impl;

import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.mapper.UserMapper;
import com.example.kokotaserver.service.IUserService;

import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  private static final String SALT = "salt";

  @Override
  public long registerUser(String userAccount, String userPassword, String checkPassword) {
    // 判空
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
      return -1;
    }
    // 账户不小于4位
    if (userAccount.length() < 4) {
      return -1;
    }
    // 密码不小于8位
    if (userPassword.length() < 8) {
      return -1;
    }
    // 账户不包含特殊字符
    if (!userAccount.matches("^[a-zA-Z0-9_]+$")) {
      return -1;
    }
    // 密码和确认密码相同
    if (!userPassword.equals(checkPassword)) {
      return -1;
    }
    // 账户不可重复
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    if (this.count(queryWrapper) > 0) {
      return -1;
    }
    // 密码加密
    String encryptedPassword = DigestUtils.md5DigestAsHex(Objects.requireNonNull((SALT + userPassword).getBytes()));
    // 插入用户
    User user = User.builder()
        .userAccount(userAccount)
        .userPassword(encryptedPassword)
        .build();
    boolean saveResult = this.save(user);
    if (!saveResult) {
      return -1;
    }
    return user.getId();
  }

  @Override
  public User login(String userAccount, String userPassword) {
    // 判空
    if (StringUtils.isAnyBlank(userAccount, userPassword)) {
      return null;
    }
    // 账户不小于4位
    if (userAccount.length() < 4) {
      return null;
    }
    // 密码不小于8位
    if (userPassword.length() < 8) {
      return null;
    }
    // 账户不包含特殊字符
    if (!userAccount.matches("^[a-zA-Z0-9_]+$")) {
      return null;
    }
    // 密码加密
    String encryptedPassword = DigestUtils.md5DigestAsHex(Objects.requireNonNull((SALT + userPassword).getBytes()));
    // 查询用户
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    queryWrapper.eq("userPassword", encryptedPassword);
    User user = this.getOne(queryWrapper);
    if (user == null) {
      log.info("用户不存在，userAccount={}", userAccount);
      return null;
    }
    return user;
  }

}
