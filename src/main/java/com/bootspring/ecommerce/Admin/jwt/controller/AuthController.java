package com.bootspring.ecommerce.Admin.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.bootspring.ecommerce.Admin.jwt.model.AuthenticationRequest;
import com.bootspring.ecommerce.Admin.jwt.model.AuthenticationResponse;
import com.bootspring.ecommerce.Admin.jwt.service.MyUserDetailsService;
import com.bootspring.ecommerce.Admin.jwt.util.JwtUtil;

@RestController
//@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    	    try {
    	        // Print authenticationRequest to check incoming data
//    	        System.out.println("Incoming Authentication Request: " + authenticationRequest);

    	        // Authenticate using authenticationManager
//    	        authenticationManager.authenticate(
//    	                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//    	        );

    	        // Print authenticationRequest after authentication to verify data integrity
//    	        System.out.println("Authentication Request after AuthManager: " + authenticationRequest);

    	        // Load user details and generate JWT token
    	        final UserDetails userDetails = userDetailsService.loadUserByUsernameAndRole(authenticationRequest.getUsername(), authenticationRequest.getRole());
    	        final String jwt = jwtTokenUtil.generateToken(userDetails,authenticationRequest.getRole());

    	        // Return JWT token in AuthenticationResponse
    	        AuthenticationResponse response = new AuthenticationResponse(jwt);
    	        return ResponseEntity.ok(response);
    	    } catch (BadCredentialsException e) {
    	        // Handle authentication failure
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    	    }
    	}

    

    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authenticate(authenticationRequest);
    }
}
