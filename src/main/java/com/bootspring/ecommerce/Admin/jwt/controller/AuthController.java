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
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    public ResponseEntity<?> authenticate( String Username,String Password) throws Exception {
        try {
            // Authenticate using authenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(Username,Password)
            );
        } catch (BadCredentialsException e) {
            // Throw exception if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Load user details and generate JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(Username);
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        // Return JWT token in AuthenticationResponse
        AuthenticationResponse response = new AuthenticationResponse(jwt);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
    }
}
