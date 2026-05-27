package com.example.kokotaserver.common;

/**
 * 响应状态码枚举类
 */
public enum ResponseCode {
  SUCCESS(0, "成功", ""),
  PARAMS_ERROR(4000, "参数错误", ""),
  NO_LOGIN(40100, "未登录", ""),
  NO_AUTH(40101, "未授权", ""),
  INTERNAL_ERROR_ERROR(5000, "内部错误", "");

  private final int code;
  private final String message;
  private final String description;

  ResponseCode(int code, String message, String description) {
    this.code = code;
    this.message = message;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getDescription() {
    return description;
  }
}
