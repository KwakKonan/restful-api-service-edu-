package com.skplanet.web.support;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.skplanet.common.BusinessException;
import com.skplanet.common.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private MessageSource messageSource;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> businessException(BusinessException exception, Locale locale) {
    return ErrorResponseEntity.badReqeust(messageSource.getMessage(exception, locale));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, Locale locale) {
    return ErrorResponseEntity.notFound(messageSource.getMessage(exception, locale));
  }

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

}
