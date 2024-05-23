package com.bootspring.ecommerce.Admin.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Admins")
public class AdminUser {
	@Id
   private String Id;
   private String name;
   private String username;
   private String password;
   
   public AdminUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminUser(String id, String name, String username ,String password) {
		super();
		Id = id;
		this.name = name;
		this.password = password;
		this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
