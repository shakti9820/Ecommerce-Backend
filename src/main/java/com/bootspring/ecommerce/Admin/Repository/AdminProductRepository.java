package com.bootspring.ecommerce.Admin.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootspring.ecommerce.Admin.Entity.AdminProduct;

public interface AdminProductRepository extends MongoRepository<AdminProduct, String>{
	AdminProduct findByAdminId(String adminId);
}
