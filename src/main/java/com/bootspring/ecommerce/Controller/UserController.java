package com.bootspring.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bootspring.ecommerce.Service.UserServices;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
    private UserServices jwtUserDetailsService;

    @GetMapping("user-id")
    public String getUserId(@RequestHeader("Authorization") String token) {
        // Assuming the token is passed in the format "Bearer <token>"
        String actualToken = token.substring(7);
        return jwtUserDetailsService.getUserDetailsByToken(actualToken);
    }
   
}
