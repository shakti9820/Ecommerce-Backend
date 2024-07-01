package com.bootspring.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bootspring.ecommerce.Entity.User;
import com.bootspring.ecommerce.Service.UserServices;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
    private UserServices jwtUserDetailsService;

    @GetMapping("user-id")
    public String getUserId(@RequestHeader("Authorization") String token) {
       
        String actualToken = token.substring(7);
        return jwtUserDetailsService.getUserIdByToken(actualToken);
    }
    
    @GetMapping("user-details")
    public ResponseEntity<Object> getUserDetails(@RequestHeader("Authorization") String token) {
       
        String actualToken = token.substring(7);
        User user= jwtUserDetailsService.getUserDetailsByToken(actualToken);
        
        return ResponseEntity.ok(user);
    }
   
}
