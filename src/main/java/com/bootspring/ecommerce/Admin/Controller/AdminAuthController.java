package com.bootspring.ecommerce.Admin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootspring.ecommerce.Admin.Entity.AdminUser;
import com.bootspring.ecommerce.Admin.Service.AdminService;

@Controller
@RequestMapping("/admin")
@CrossOrigin
public class AdminAuthController {
    
	private final AdminService adminService;
	
	@Autowired
	public AdminAuthController(AdminService adminService) {
		this.adminService=adminService;
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<Object> adminSignUp(@RequestBody AdminUser admin) {
		
	    String username = admin.getUsername();
	    
	    
	    // Check if the user already exists
	    AdminUser existingUser = adminService.findByUsername(username);
	    
	    if (existingUser != null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin with username " + username + " already exists");
	    }
	    
	    // User does not exist, proceed with signup
	    AdminUser savedUser = adminService.saveAdminUser(admin);
	    
	    // Create a DTO for sending user data to the frontend
	    AdminUser userDTO = new AdminUser();
	    userDTO.setId(savedUser.getId());
	    userDTO.setUsername(savedUser.getUsername());
	    userDTO.setName(savedUser.getName());	    
	    return ResponseEntity.ok(userDTO);
	}

	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Object> adminLogin(@RequestBody AdminUser admin) {
		
	    String username = admin.getUsername();
	    String password = admin.getPassword();
	    

	    AdminUser loggedInAdmin = adminService.findByUsernameAndPassword(username, password);
	    
	    if (loggedInAdmin == null) {
//	    	System.out.println("hello from login");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
	    }
	    AdminUser userDTO = new AdminUser();
	    userDTO.setId(loggedInAdmin.getId());
	    userDTO.setUsername(loggedInAdmin.getUsername());
	    userDTO.setName(loggedInAdmin.getName());	    
	    return ResponseEntity.ok(userDTO);
	    
	}
}
