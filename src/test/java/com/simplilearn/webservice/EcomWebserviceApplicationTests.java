package com.simplilearn.webservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.controller.BaseController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Application Test : Main Test")
class EcomWebserviceApplicationTests {
	
	@Autowired
	private BaseController controller;
	
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private TestRestTemplate template;
	

	@Test
	@DisplayName("Test : Load Application Context")
	void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	@DisplayName("Test : Server On & Running")
	void testOnServer() {
		String url ="http://localhost:"+randomPort+"/";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		String expected ="Spring Application server is Up & Running !";
		assertEquals(expected, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test : Hello Request Mapping +ve")
	void testOnServer2() {
		String url ="http://localhost:"+randomPort+"/hello?username=John Smith";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		String expected ="Hello, user John Smith";
		assertEquals(expected, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	
	@Test
	@DisplayName("Test : Hello Request Mapping -ve")
	void testOnServer3() {
		String url ="http://localhost:"+randomPort+"/hello?username=Will Smith";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		String expected ="Hello, user John Smith";
		assertNotEquals(expected, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
}
