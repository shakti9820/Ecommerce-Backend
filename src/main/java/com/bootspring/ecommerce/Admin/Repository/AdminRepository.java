package com.bootspring.ecommerce.Admin.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Admin.Entity.AdminUser;

public interface AdminRepository extends MongoRepository<AdminUser, String>{

	 AdminUser findByUsernameAndPassword(String username, String password);

	AdminUser findByUsername(String username);
	

}
