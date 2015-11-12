package com.skplanet.web.support;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ErrorResponseEntity extends ResponseEntity<ErrorResponse> {

  public ErrorResponseEntity(ErrorResponse body) {
    super(body, body.getStatus());
  }

  public ErrorResponseEntity(ErrorResponse body, MultiValueMap<String, String> headers) {
    super(body, headers, body.getStatus());
  }

  public static ErrorResponseEntity badReqeust(String message) {
    return new ErrorResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, message));
  }
  
  public static ErrorResponseEntity badReqeust(String message, List<String> bindingResults) {
    return new ErrorResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, message, bindingResults));
  }

  public static ErrorResponseEntity notFound(String message) {
    return new ErrorResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, message));
  }
  
  public static ErrorResponseEntity serverError(String message) {
    return new ErrorResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message));
  }

}
