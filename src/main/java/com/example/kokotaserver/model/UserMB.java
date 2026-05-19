package com.example.kokotaserver.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("user_mb")
public class UserMB {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}