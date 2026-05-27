package com.example.kokotaserver.common;

/**
 * 响应工具类
 */
public class ResponseUtil {

  public static <T> BaseResponse<T> success(T data) {
    return BaseResponse.<T>builder()
        .code(ResponseCode.SUCCESS.getCode())
        .message(ResponseCode.SUCCESS.getMessage())
        .description(ResponseCode.SUCCESS.getDescription())
        .data(data)
        .build();
  }

  public static <T> BaseResponse<T> error(ResponseCode responseCode) {
    return BaseResponse.<T>builder()
        .code(responseCode.getCode())
        .message(responseCode.getMessage())
        .description(responseCode.getDescription())
        .data(null)
        .build();
  }

  public static <T> BaseResponse<T> error(Integer code, String message, String description) {
    return BaseResponse.<T>builder()
        .code(code)
        .message(message)
        .description(description)
        .data(null)
        .build();
  }
}
