package com.simplilearn.webservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.model.Product;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller Test")
public class ProductControllerTest {

	@LocalServerPort
	private int randomPort;

	@Autowired
	private TestRestTemplate template;

	@Test
	@DisplayName("Test :: List all products")
	void testGetAllProducts() {
		// defined a product url
		String url = "http://localhost:" + randomPort + "/products";
		// Get all products
		ResponseEntity<List> response = template.getForEntity(url, List.class);

		assertEquals(false, response.getBody().isEmpty());
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Test :: List one product")
	void testGetOneProduct() {
		// defined a product url
		String url = "http://localhost:" + randomPort + "/products/3";
		// Get all products
		ResponseEntity<Product> response = template.getForEntity(url, Product.class);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(3, response.getBody().getId());
		assertEquals("Dell slim book 98456ERSD series", response.getBody().getName());
	}

	@Test
	@DisplayName("Test :: Get one product which does not exist")
	public void testGetOneProductNotExist() {
		String url = "http://localhost:" + randomPort + "/products/1200";
		// Get all products
		ResponseEntity<Product> response = template.getForEntity(url, Product.class);
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Test :: Add one product")
	public void testAddOneProduct() {
		// defined a product url
		String url = "http://localhost:" + randomPort + "/products";

		// create product object
		Product product = new Product("Dell Laptop xyz series", 4545.45, "It is a laptop", true);

		// Http request entity object
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		ResponseEntity<Product> response = template.postForEntity(url, requestObj,Product.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("Dell Laptop xyz series", response.getBody().getName());
	}
	
	@Test
	@DisplayName("Test :: Add one product with null value")
	public void testAddOneNullProduct() {
		String url ="http://localhost:"+randomPort+"/products";
		
		//create product object
		Product product = null;
		
		//Http request entity object
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		//add one products
		ResponseEntity<Product> response = template.postForEntity(url, requestObj,Product.class);
		
		assertEquals(415, response.getStatusCodeValue());
		
	}
	
	// TODO : WAUT For update and delete products
}
