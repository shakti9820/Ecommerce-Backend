package com.bootspring.ecommerce.Customer.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Customer.Entity.CustomerUser;

public interface CustomerRepository extends MongoRepository<CustomerUser, String>{

	CustomerUser findByUsernameAndPassword(String username, String password);

	CustomerUser findByUsername(String username);

}
