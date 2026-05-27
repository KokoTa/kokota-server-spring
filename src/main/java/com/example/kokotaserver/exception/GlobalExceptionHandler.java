package com.example.kokotaserver.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kokotaserver.common.BaseResponse;
import com.example.kokotaserver.common.ResponseCode;
import com.example.kokotaserver.common.ResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public BaseResponse<?> handleBusinessException(BusinessException exception) {
    return ResponseUtil.error(exception.getCode(), exception.getMessage(), exception.getDescription());
  }

  @ExceptionHandler(RuntimeException.class)
  public BaseResponse<?> handleRuntimeException(RuntimeException exception) {
    return ResponseUtil.error(ResponseCode.INTERNAL_ERROR_ERROR.getCode(), exception.getMessage(),
        exception.getMessage());
  }
}
