package com.bootspring.ecommerce.Customer.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customers")
public class CustomerUser {
       @Id
	   private String Id;
	   private String Name;
	   private String username;
	   private String password;
	   
	   public CustomerUser() {
			super();
			
		}

		public CustomerUser(String id, String name, String username ,String password) {
			super();
			Id = id;
			Name = name;
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
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
}
