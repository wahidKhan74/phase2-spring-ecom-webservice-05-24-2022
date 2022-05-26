package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.exception.AlreadyExist;
import com.simplilearn.webservice.exception.InvalidException;
import com.simplilearn.webservice.exception.NotFound;
import com.simplilearn.webservice.model.Product;

@RestController
public class ProductController {

	List<Product> products = new ArrayList<Product>();

	// get all products
	@GetMapping("/products")
	public List<Product> getAll() {
		if (products.isEmpty()) {
			addDefaults();
		}
		return products;
	}

	// get one by id
	@GetMapping("/products/{id}")
	public Product getOne(@PathVariable("id") int id) {
		// add a products list
		if (products.isEmpty()) {
			addDefaults();
		}
		// find a product
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new NotFound("Product Not found with given productId "+id);
	}

	// get one by name
	@GetMapping("/product")
	public Product getOne(@RequestParam("name") String name) {
		// add a products list
		if (products.isEmpty()) {
			addDefaults();
		}
		// find a product
		for (Product product : products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		throw new NotFound("Product Not found with given name "+name);
	}

	// search one by name
	@GetMapping("/product/search")
	public Product searchOne(@RequestParam("name") String name) {
		// add a products list
		if (products.isEmpty()) {
			addDefaults();
		}
		// find a product
		for (Product product : products) {
			if (product.getName().contains(name)) {
				return product;
			}
		}
		throw new NotFound("Product Not found with given name "+name);
	}

	// add one
	@PostMapping("/products")
	public Product addOne(@RequestBody(required=false) Product product) {		
		
		if (product != null) {
			for (Product prod : products) {
				if (prod.getId() == product.getId()) {
					throw new AlreadyExist("Product can not be create with id "+product.getId());
				}
			}			
			products.add(product);
			return product;
		}
		
		throw new InvalidException("Product can not be create without product data ="+product);
	}

	// update one
	@PutMapping("/products")
	public Product updateOne(@RequestBody Product product) {
		if (product != null) {
			for (int index = 0; index < products.size(); index++) {
				if (products.get(index).getId() == product.getId()) {
					// replace /update on products list object
					products.set(index, product);
					return product;
				}
			}
		}
		return null;
	}

	// delete one
	@DeleteMapping("/products/{id}")
	public Product deleteOne(@PathVariable("id") int id) {
			for (int index = 0; index < products.size(); index++) {
				if (products.get(index).getId() == id) {
					// delete from products list					
					return products.remove(index);
				}
			}
		return null;
	}

	// add default products
	private void addDefaults() {
		products.add(new Product(10001, "HP 10012RED Model laptop", 993.994, "It is a laptop", true));
		products.add(new Product(10002, "Apple mac book 9345MSLV series", 89993.994, "It is a laptop", true));
		products.add(new Product(10003, "Dell slim book 98456ERSD series", 7723.994, "It is a laptop", false));
		products.add(new Product(10004, "Lenovo slim book QURW954756 model", 3723.994, "It is a laptop", true));
	}
}
