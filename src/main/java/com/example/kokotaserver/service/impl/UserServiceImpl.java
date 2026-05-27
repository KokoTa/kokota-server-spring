package com.example.kokotaserver.service.impl;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kokotaserver.common.ResponseCode;
import com.example.kokotaserver.constant.UserConstant;
import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.exception.BusinessException;
import com.example.kokotaserver.mapper.UserMapper;
import com.example.kokotaserver.service.IUserService;

import lombok.extern.slf4j.Slf4j;

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
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "参数不能为空");
    }
    // 账户不小于4位
    if (userAccount.length() < 4) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "账户长度小于4位");
    }
    // 密码不小于8位
    if (userPassword.length() < 8) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "密码长度小于8位");
    }
    // 账户不包含特殊字符
    if (!userAccount.matches("^[a-zA-Z0-9_]+$")) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "账户包含特殊字符");
    }
    // 密码和确认密码相同
    if (!userPassword.equals(checkPassword)) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "密码和确认密码不一致");
    }
    // 账户不可重复
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    if (this.count(queryWrapper) > 0) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "账户已存在");
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
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "注册失败");
    }
    return user.getId();
  }

  @Override
  public User login(String userAccount, String userPassword, HttpServletRequest request) {
    // 判空
    if (StringUtils.isAnyBlank(userAccount, userPassword)) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "参数不能为空");
    }
    // 账户不小于4位
    if (userAccount.length() < 4) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "账户长度小于4位");
    }
    // 密码不小于8位
    if (userPassword.length() < 8) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "密码长度小于8位");
    }
    // 账户不包含特殊字符
    if (!userAccount.matches("^[a-zA-Z0-9_]+$")) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "账户包含特殊字符");
    }
    // 密码加密
    String encryptedPassword = DigestUtils.md5DigestAsHex(Objects.requireNonNull((SALT + userPassword).getBytes()));
    // 查询用户
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    queryWrapper.eq("userPassword", encryptedPassword);
    User user = this.getOne(queryWrapper);
    // 用户脱敏
    user = safeUser(user);
    // 设置用户登录状态
    request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
    return user;
  }

  @Override
  public User currentUser(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
    if (user == null) {
      throw new BusinessException(ResponseCode.NO_LOGIN, "未登录");
    }
    user = getById(user.getId());
    return safeUser(user);
  }

  @Override
  public void logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    if (session.getAttribute(UserConstant.USER_LOGIN_STATE) == null) {
      throw new BusinessException(ResponseCode.NO_LOGIN, "未登录");
    }
    session.removeAttribute(UserConstant.USER_LOGIN_STATE);
  }

  @Override
  public User safeUser(User user) {
    if (user == null) {
      throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户不存在");
    }
    user.setUserPassword(null);
    user.setIsDelete(null);
    return user;
  }

}
