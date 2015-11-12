package com.skplanet.web;

import static org.junit.Assert.assertThat;

import java.net.URI;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.skplanet.RestfulApiServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestfulApiServiceApplication.class)
@WebIntegrationTest(randomPort=true)
public class BoardControllerTest {

    @Value("${local.server.port}")
    private int serverPort;
    
    @Test
    public void 게시판_목록을_조회한다() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        
        URI uriBoards = UriComponentsBuilder.fromPath("/boards")
                                            .scheme("http")
                                            .host("localhost")
                                            .port(serverPort)
                                            .build()
                                            .toUri();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(uriBoards, JsonNode.class);
                
        org.junit.Assert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(HttpStatus.OK));
        org.junit.Assert.assertThat(response.getBody().isArray(), org.hamcrest.Matchers.is(true));
    }
    
    @Test
    public void 하나의_게시판을_조회한다() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        
        URI uriBoards = UriComponentsBuilder.fromPath("/boards/{boardName}/info")
                                            .scheme("http")
                                            .host("localhost")
                                            .port(serverPort)
                                            .buildAndExpand("general")
                                            .toUri();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(uriBoards, JsonNode.class);
                
        org.junit.Assert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(HttpStatus.OK));
        org.junit.Assert.assertThat(response.getBody().path("name").asText(), org.hamcrest.Matchers.is("general"));
    }
    
    @Test
    public void 존재하지_않는_게시판을_조회하면_오류가_발생한다() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        
        URI uriBoards = UriComponentsBuilder.fromPath("/boards/{boardName}/info")
                                            .scheme("http")
                                            .host("localhost")
                                            .port(serverPort)
                                            .buildAndExpand("noboard")
                                            .toUri();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(uriBoards, JsonNode.class);
                
        org.junit.Assert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(HttpStatus.NOT_FOUND));
        org.junit.Assert.assertThat(response.getBody().path("error").asText(), org.hamcrest.Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
    
}
