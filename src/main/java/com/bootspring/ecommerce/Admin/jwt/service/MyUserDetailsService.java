package com.bootspring.ecommerce.Admin.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Admin.Entity.AdminUser;
import com.bootspring.ecommerce.Admin.Repository.AdminRepository;
import com.bootspring.ecommerce.Customer.Entity.CustomerUser;
import com.bootspring.ecommerce.Customer.Repository.CustomerRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public UserDetails loadUserByUsernameAndRole(String username, String role) throws UsernameNotFoundException {
    	System.out.println((username));
    	System.out.println(role);
        if ("user".equalsIgnoreCase(role)) {
            CustomerUser user = userRepository.findByUsername(username);
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
            }
        } else if ("admin".equalsIgnoreCase(role)) {
            AdminUser admin = adminRepository.findByUsername(username);
            if (admin != null) {
                return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + username + " and role: " + role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Use loadUserByUsernameAndRole instead");
    }
}
