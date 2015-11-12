package com.skplanet.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.skplanet.RestfulApiServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestfulApiServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class RestAssuredBoardControllerTest {
	
	@Value("${local.server.port}")
	private int serverPort;
	
	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	@Test
	public void 게시판_목록을_조회한다() {		
		RestAssured.when().get("/boards")
				   .then().statusCode(HttpStatus.OK.value())
				   	      .body("$", Matchers.hasSize(2));
	}
	
	@Test
	public void 하나의_게시판_정보를_조회한다() {		
		RestAssured.when().get("/boards/{boardName}/info", "general")
				   .then().statusCode(HttpStatus.OK.value())
				   	      .body("name", Matchers.is("general"));
	}
	
	@Test
	public void 존재하지_않는_게시판을_조회하면_404_오류가_반환된다() {		
		RestAssured.when().get("/boards/{boardName}/info", "noboard")
				   .then().statusCode(HttpStatus.NOT_FOUND.value())
				   	      .body("error", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase()));
	}

}
