package com.simplilearn.webservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simplilearn.webservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	// derived query in spring boot and data jpa
	public Product findByName(String name);
	public Product findByPrice(double price);
	public Product findByAvailable(boolean available);
	
	
	@Query("select p from Product p where p.name LIKE %?1%")
	List<Product> searchByName(String name);

}
