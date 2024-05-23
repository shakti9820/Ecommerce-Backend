package com.bootspring.ecommerce.Customer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Customer.Entity.CustomerUser;
import com.bootspring.ecommerce.Customer.Repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerUser saveCustomerUser(CustomerUser customer) {
        return customerRepository.save(customer);
    }

    public CustomerUser findByUsernameAndPassword(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }

    public CustomerUser findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

	
}
