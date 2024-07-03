
package com.bootspring.ecommerce.Customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootspring.ecommerce.Admin.jwt.controller.AuthController;
import com.bootspring.ecommerce.Admin.jwt.model.AuthenticationRequest;
import com.bootspring.ecommerce.Admin.jwt.model.AuthenticationResponse;
import com.bootspring.ecommerce.Customer.Entity.CustomerUser;
import com.bootspring.ecommerce.Customer.Service.CustomerService;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class CustomerAuthController {
    
	private final CustomerService customerService;
	
    private final AuthController authController;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerAuthController(CustomerService customerService, AuthController authController) {
		this.customerService=customerService;
		this.authController=authController;
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<Object> adminSignUp(@RequestBody CustomerUser customer) {
		 String username = customer.getUsername();
		    
		    
		    // Check if the user already exists
		    CustomerUser existingUser = customerService.findByUsername(username);
		    
		    if (existingUser != null) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer with username " + username + " already exists");
		    }
		    
		    String encodedPassword =passwordEncoder.encode(customer.getPassword());
	        customer.setPassword(encodedPassword);
		    
		    // User does not exist, proceed with signup
		    CustomerUser savedUser = customerService.saveCustomerUser(customer);
		    
		    // Create a DTO for sending user data to the frontend
		    CustomerUser userDTO = new CustomerUser();
		    userDTO.setId(savedUser.getId());
		    userDTO.setUsername(savedUser.getUsername());
		    userDTO.setName(savedUser.getName());	    
		    return ResponseEntity.ok(userDTO);
	}
	
	 @PostMapping("/login")
	 @ResponseBody
	    public ResponseEntity<Object> customerLogin(@RequestBody AuthenticationRequest authenticationRequest) {
	        
		 try {
		 String username = authenticationRequest.getUsername();
	        String password = authenticationRequest.getPassword();
	        
	        ResponseEntity<?> authenticatedResponse=authController.createAuthenticationToken(authenticationRequest);
//	        System.out.println(password);
	        if (authenticatedResponse.getStatusCode().is2xxSuccessful()) {
	        	
	        	AuthenticationResponse authResponse = (AuthenticationResponse) authenticatedResponse.getBody();
	            return ResponseEntity.ok(authResponse);
	        } else {
	            // Handle authentication failure
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	        }catch (Exception e) {
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
			}

//	        CustomerUser loggedInCustomer = customerService.findByUsernameAndPassword(username, password);
//
//	        if (loggedInCustomer == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
//	        }
//             
//	        CustomerUser userDTO = new CustomerUser();
//		    userDTO.setId(loggedInCustomer.getId());
//		    userDTO.setUsername(loggedInCustomer.getUsername());
//		    userDTO.setName(loggedInCustomer.getName());	    
//		    return ResponseEntity.ok(userDTO);
	        
	    }
}
