package com.bootspring.ecommerce.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
	    @Id
	    private String id;
	    private String name;
	    private String highlights;
	    private String description;
	    private double price;
	    private String imageUrl;
		public Product() {
			
		}
		public Product(String id, String name,String highlights, String description, double price, String imageUrl) {
			this.name = name;
			this.highlights=highlights;
			this.description = description;
			this.price = price;
			this.imageUrl = imageUrl;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getHighlights() {
			return highlights;
		}
		public void setHighlights(String highlights) {
			this.highlights = highlights;
		}
	    
	    
}
