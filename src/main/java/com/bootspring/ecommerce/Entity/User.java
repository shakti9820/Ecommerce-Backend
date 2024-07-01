package com.bootspring.ecommerce.Entity;

import org.springframework.data.annotation.Id;

public class User {
	  @Id
	   private String Id;
	   private String name;
	   private String username;
	   
	   public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(String id, String name, String username) {
			super();
			Id = id;
			this.name = name;
			this.username=username;
		}

		@Override
		public String toString() {
			return "User [Id=" + Id + ", name=" + name + ", username=" + username + "]";
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

}
