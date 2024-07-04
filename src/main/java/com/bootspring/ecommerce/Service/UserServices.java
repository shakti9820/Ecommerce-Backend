package com.bootspring.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Admin.Entity.AdminUser;
import com.bootspring.ecommerce.Admin.Repository.AdminRepository;
import com.bootspring.ecommerce.Admin.jwt.util.JwtUtil;
import com.bootspring.ecommerce.Customer.Entity.CustomerUser;
import com.bootspring.ecommerce.Customer.Repository.CustomerRepository;
import com.bootspring.ecommerce.Entity.User;

@Service
public class UserServices {

	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepository; 
    
    @Autowired
    private CustomerRepository customerRepository; 

    public String getUserIdByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        AdminUser adminUser = adminRepository.findByUsername(username);
        if (adminUser == null) {
            throw new UsernameNotFoundException("Admin Id not found");
        }
        
        return adminUser.getId();
    }
    
    public User getUserDetailByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        CustomerUser customerUser = customerRepository.findByUsername(username);
        if (customerUser == null) {
            throw new UsernameNotFoundException("User Id not found");
        }
        
        User user=new User(customerUser.getId(), customerUser.getName(),customerUser.getUsername()); 
        return user;
    }
    
    public User getAdminDetailsByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        AdminUser adminUser = adminRepository.findByUsername(username);
        if (adminUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User user=new User(adminUser.getId(), adminUser.getName(),adminUser.getUsername()); 
        return user;
    }
    
}
