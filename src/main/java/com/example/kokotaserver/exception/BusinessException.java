package com.example.kokotaserver.exception;

import com.example.kokotaserver.common.ResponseCode;

import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {

  private final Integer code;

  private final String description;

  public BusinessException(ResponseCode responseCode, String description) {
    super(responseCode.getMessage());
    this.code = responseCode.getCode();
    this.description = description;
  }
}
