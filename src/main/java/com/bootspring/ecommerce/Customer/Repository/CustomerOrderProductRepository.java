package com.bootspring.ecommerce.Customer.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Customer.Entity.CustomerOrderProduct;

public interface CustomerOrderProductRepository extends MongoRepository<CustomerOrderProduct, String> {

	CustomerOrderProduct findByCustomerId(String customerId);

}
