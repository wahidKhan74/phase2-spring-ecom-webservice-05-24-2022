package com.simplilearn.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Application Test : GreetingServiceTest")
public class GreetingServiceTest {

	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private TestRestTemplate template;
	
	
	@Test
	@DisplayName("Test : Greet Morning")
	void testOnGreetAPI1() {
		String url ="http://localhost:"+randomPort+"/greet?time=6";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		String expected ="Good Morning!, You start a wonderfull day.";
		assertEquals(expected, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test : Greet Afternoon")
	void testOnGreetAPI2() {
		String url ="http://localhost:"+randomPort+"/greet?time=14";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		String expected ="Good Afternoon!, Best of luck for the other of the day";
		assertEquals(expected, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
}

