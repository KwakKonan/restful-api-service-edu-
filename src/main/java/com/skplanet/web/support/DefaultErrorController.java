package com.skplanet.web.support;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class DefaultErrorController implements ErrorController {

  private static final String ERROR_PATH = "${error.path:/error}";
  private static final String ERROR_DEFAULT_MESSAGE = "No message available.";
  
  private String errorPath;
  private ErrorAttributes errorAttributes;
  private MessageSource messageSource;

  @RequestMapping(value=ERROR_PATH)
  public ResponseEntity<Map<String, Object>> error(HttpServletRequest request, Locale locale) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    Map<String, Object> body = errorAttributes.getErrorAttributes(requestAttributes, true);

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    if (Objects.nonNull(body.get("status"))) {
      try {
        status = HttpStatus.valueOf((Integer) body.get("status"));
      } catch (Exception ignore) {
      }
    }

    String message = messageSource.getMessage("error." + status, null, ERROR_DEFAULT_MESSAGE, locale);
    if (StringUtils.hasText(message)) {
      body.put("message", message);
    }

    return ResponseEntity.status(status).body(body);
  }

  @Override
  public String getErrorPath() {
    return errorPath;
  }

  @Value(ERROR_PATH)
  public void setErrorPath(String errorPath) {
    this.errorPath = errorPath;
  }

  @Autowired
  public void setErrorAttributes(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

}