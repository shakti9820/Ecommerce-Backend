package com.bootspring.ecommerce.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	Product getProductById(String productId);



}
