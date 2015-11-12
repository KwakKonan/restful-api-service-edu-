package com.skplanet.web.support;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private final HttpStatus status;
  private final String error;
  private final String message;
  private final Date timestamp;
  private List<String> bindingResults;

  public ErrorResponse(HttpStatus status, String message) {
    this(status, status.getReasonPhrase(), message);
  }
  
public ErrorResponse(HttpStatus status, String message, List<String> bindingResults) {
    this(status, status.getReasonPhrase(), message);
    this.bindingResults = bindingResults;
  }

  public ErrorResponse(HttpStatus status, String error, String message) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.timestamp = new Date();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public List<String> getBindingResults() {
    return bindingResults;
  }
  
}
