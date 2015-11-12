package com.skplanet.common;

import org.springframework.context.MessageSourceResolvable;

public abstract class ResourceNotFoundException extends RuntimeException implements MessageSourceResolvable {
  
  public ResourceNotFoundException(String message) {
    super(message);
  }
  
  @Override
  public Object[] getArguments() {
    return null;
  }  

  @Override
  public String getDefaultMessage() {
    return getMessage();
  }

}