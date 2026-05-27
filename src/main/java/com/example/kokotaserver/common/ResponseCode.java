package com.example.kokotaserver.common;

public enum ResponseCode {
  SUCCESS(0, "成功"),
  PARAMS_ERROR(4000, "参数错误"),
  PARAMS_NULL_ERROR(40001, "参数不能为空"),
  NO_LOGIN(40100, "未登录"),
  NO_AUTH(40101, "未授权");

  private final int code;
  private final String message;

  ResponseCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
