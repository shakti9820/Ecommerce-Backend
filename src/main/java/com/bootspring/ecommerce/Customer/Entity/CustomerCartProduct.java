package com.bootspring.ecommerce.Customer.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "CustomerCartProduct")
public class CustomerCartProduct {
    @Id
    private String id;
    private String customerId;
    private List<String> productIds;

    public CustomerCartProduct() {
    }

    public CustomerCartProduct(String customerId, List<String> productIds) {
        this.customerId = customerId;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

	
    
}
