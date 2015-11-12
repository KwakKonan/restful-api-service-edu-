package com.skplanet.web.support.jackson2;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class HttpStatusSerializer extends JsonSerializer<HttpStatus> {

  @Override
  public void serialize(HttpStatus status, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    jgen.writeNumber(status.value());
  }

}