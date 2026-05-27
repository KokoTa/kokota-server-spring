package com.example.kokotaserver.common;

public class ResponseUtil {

  public static <T> BaseResponse<T> success(T data) {
    return BaseResponse.<T>builder()
        .code(ResponseCode.SUCCESS.getCode())
        .message(ResponseCode.SUCCESS.getMessage())
        .data(data)
        .build();
  }

  public static <T> BaseResponse<T> error(ResponseCode responseCode) {
    return BaseResponse.<T>builder()
        .code(responseCode.getCode())
        .message(responseCode.getMessage())
        .data(null)
        .build();
  }
}
