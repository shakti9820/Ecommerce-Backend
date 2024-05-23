package com.bootspring.ecommerce.Customer.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Customer.Entity.CustomerCartProduct;



public interface CustomerCartProductRepository extends MongoRepository<CustomerCartProduct, String>{

	CustomerCartProduct findByCustomerId(String customerId);

}
