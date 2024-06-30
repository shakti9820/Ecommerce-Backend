package com.bootspring.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Admin.jwt.util.JwtUtil;
import com.bootspring.ecommerce.Customer.Entity.CustomerUser;

@Service
public class UserServices {

	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public String getUserDetailsByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        // Assuming your UserDetails implementation has a method to get the user ID
        if (userDetails instanceof CustomerUser) {
            return ((CustomerUser) userDetails).getId();
        }
        
        return null;
    }
    
}
