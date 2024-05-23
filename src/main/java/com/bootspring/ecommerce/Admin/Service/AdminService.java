package com.bootspring.ecommerce.Admin.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Admin.Entity.AdminUser;
import com.bootspring.ecommerce.Admin.Repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    
    @Autowired
    public AdminService(AdminRepository adminRepository) {
    	this.adminRepository=adminRepository;
    }

	public AdminUser saveAdminUser(AdminUser adminUser) {
		adminRepository.save(adminUser);
    	return adminUser;
	}

	public AdminUser findByUsernameAndPassword(String username, String password) {
		
        AdminUser user= adminRepository.findByUsernameAndPassword(username, password);

         return user;
    }

	public AdminUser findByUsername(String username) {
		AdminUser user= adminRepository.findByUsername(username);
        return user;
	}
    
}
