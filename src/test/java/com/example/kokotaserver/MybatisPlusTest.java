package com.example.kokotaserver;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.kokotaserver.entity.UserMb;
import com.example.kokotaserver.mapper.UserMbMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusTest {

  @Autowired
  private UserMbMapper userMbMapper;

  @Test
  public void testSelect() {
    System.out.println(("----- selectAll method test ------"));
    List<UserMb> userList = userMbMapper.selectList(null);
    Assert.assertEquals(5, userList.size());
    userList.forEach(System.out::println);
  }

}
