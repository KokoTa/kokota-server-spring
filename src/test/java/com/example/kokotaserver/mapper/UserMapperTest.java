package com.example.kokotaserver.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kokotaserver.entity.User;
import com.example.kokotaserver.service.IUserService;

import net.datafaker.Faker;

@SpringBootTest
public class UserMapperTest {

  @Autowired
  private IUserService userService;

  @Test
  public void testAdd() {
    Faker faker = new Faker();
    User user = User.builder()
        .username(faker.name().firstName())
        .userAccount(faker.name().lastName())
        .userPassword(faker.internet().uuid())
        .build();
    boolean save = userService.save(user);
    Assertions.assertTrue(save);
  }
}
