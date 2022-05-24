package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.model.Product;

@RestController
public class ProductController {

	List<Product> products = new ArrayList<Product>();

	// get all products
	@GetMapping("/products")
	public List<Product> getProducts() {
		if (products.isEmpty()) {
			addDefaults();
		}
		return products;
	}

	// add default products
	private void addDefaults() {
		products.add(new Product(10001, "HP 10012RED Model laptop", 993.994, "It is a laptop", true));
		products.add(new Product(10002, "Apple mac book 9345MSLV series", 89993.994, "It is a laptop", true));
		products.add(new Product(10003, "Dell slim book 98456ERSD series", 7723.994, "It is a laptop", false));
		products.add(new Product(10004, "Lenovo slim book QURW954756 model", 3723.994, "It is a laptop", true));
	}
}
