package com.bootspring.ecommerce.Admin.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "AdminProduct")
public class AdminProduct {

    @Id
    private String id;
    private String adminId;
    private List<String> productIds;

    // Constructors, getters, and setters
    public AdminProduct() {
    }

    public AdminProduct(String adminId, List<String> productIds) {
        this.adminId = adminId;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
