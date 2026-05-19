package com.example.kokotaserver.service.impl;

import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.mapper.UserMapper;
import com.example.kokotaserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author KokoTa
 * @since 2026-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
