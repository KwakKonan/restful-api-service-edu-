package com.skplanet.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix="service.info")
public class ServiceInfoController {
  
  private String name;
  private String version;
  
  @RequestMapping(value="/info")
  public Map<String, Object> info() {
    Map<String, Object> model = new HashMap<>();
    model.put("name", name);
    model.put("version", version);
    
    return model;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}
