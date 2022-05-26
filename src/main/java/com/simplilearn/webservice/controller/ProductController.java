package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.simplilearn.webservice.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	// get all products
	@GetMapping("/products")
	public List<Product> getAll() {
		// find all products
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new NotFound("Product list is emopty");
		}
		return products;
	}

	// get one by id
	@GetMapping("/products/{id}")
	public Product getOne(@PathVariable("id") int id) {
		Optional<Product> response = productRepository.findById(id);
		if (response.isPresent()) {
			return response.get();
		}
		throw new NotFound("Product Not found with given productId " + id);
	}

	// get one by name
	@GetMapping("/product")
	public Product getOne(@RequestParam("name") String name) {
		Product product = productRepository.findByName(name); 
		if(product !=null) {
			return product;
		}
		throw new NotFound("Product Not found with given name " + name);
	}

	// search one by name
	@GetMapping("/product/search")
	public List<Product> searchOne(@RequestParam("name") String name) {
		// find a product
		List<Product> products = productRepository.searchByName(name); 
		if(!products.isEmpty()) {
			return products;
		}
		throw new NotFound("Product Not found with given name " + name);
	}

	// add one
	@PostMapping("/products")
	public Product addOne(@RequestBody(required = false) Product product) {

		if (product != null) {
			Optional<Product> response = productRepository.findById(product.getId());
			if (response.isPresent()) {
				throw new AlreadyExist("Product Already Exist with given id " + product.getId());
			}
			return productRepository.save(product);
		}
		throw new InvalidException("Product can not be created with  data = " + product);
	}

	// update one
	@PutMapping("/products")
	public Product updateOne(@RequestBody(required = false) Product product) {
		if (product != null) {
			Optional<Product> response = productRepository.findById(product.getId());
			if (response.isPresent()) {
				return productRepository.save(product);
			} else {
				throw new NotFound("Product Not found with given productId " + product.getId());
			}
		}
		throw new InvalidException("Product can not be updated with data = " + product);
	}

	// delete one
	@DeleteMapping("/products/{id}")
	public String deleteOne(@PathVariable("id") int id) {
		Optional<Product> response = productRepository.findById(id);
		if (response.isPresent()) {
			productRepository.delete(response.get());
			return "Product is deleted successfully!";
		}
		throw new NotFound("Product Not found with given productId " + id);
	}

}
