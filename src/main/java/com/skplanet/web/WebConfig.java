package com.skplanet.web;

import java.text.SimpleDateFormat;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.skplanet.BoardAppProfiles;
import com.skplanet.web.support.jackson2.HttpStatusSerializer;

@Configuration
public class WebConfig extends WebMvcAutoConfigurationAdapter {

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.ignoreAcceptHeader(true)
              .favorPathExtension(false)
              .defaultContentType(MediaType.APPLICATION_JSON);
  }
  
  @Bean
  public HttpPutFormContentFilter httpPutFormContentFilter() {
    return new HttpPutFormContentFilter();
  }  
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new LocaleChangeInterceptor());
  }

  @Bean
  @Override
  public LocaleResolver localeResolver() {
      return new SessionLocaleResolver();
  }  
  
  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }
  
  @Autowired
  public void setUpObjectMapper(ObjectMapper mapper, Environment environment) {
    if (environment.acceptsProfiles(BoardAppProfiles.LOCAL)) {
      // 로컬일때 설정
    } else {
      // 개발, 운영일때 설정
    }
    
    SimpleModule module = new SimpleModule("restful-api-service");
    module.addSerializer(HttpStatus.class, new HttpStatusSerializer());
    
    mapper.registerModule(module);
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
  } 

  @Bean
  @Profile({ BoardAppProfiles.DEV, BoardAppProfiles.PROD })
  public Filter filter() {
    // 보안 필터라고 생각하고..
    return new HiddenHttpMethodFilter();
  }
  
}
