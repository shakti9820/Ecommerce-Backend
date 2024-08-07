package com.bootspring.ecommerce.Admin.jwt.model;

public class AuthenticationRequest {

    private String username;
    private String password;
    private String role;



	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

	// Default constructor for JSON Parsing
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password ,String role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
