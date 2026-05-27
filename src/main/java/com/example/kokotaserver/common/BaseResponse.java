package com.example.kokotaserver.common;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 基础响应类
 */
@Data
@Builder
public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = 1225910564L;

  private int code;

  private T data;

  private String message;

  private String description;

}
