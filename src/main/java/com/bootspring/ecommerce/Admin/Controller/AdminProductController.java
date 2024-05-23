package com.bootspring.ecommerce.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootspring.ecommerce.Admin.Service.AdminProductService;
import com.bootspring.ecommerce.Entity.Product;
import com.bootspring.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminProductController {
      private final AdminProductService adminProductService;
    
    @Autowired
    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping("/addproduct")
    public ResponseEntity<Object> addProduct(@RequestBody Product product, @RequestParam String adminId) {
        try {
            // Add the product to the database and map it to the admin
            Product savedProduct = adminProductService.addProduct(product, adminId);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product: " + e.getMessage());
        }
    }
    
    @GetMapping("/get_admin_products")
    public ResponseEntity<Object> getAdminProducts(@RequestParam String adminId) {
//    	System.out.println(adminId);
        try {
            // Retrieve products added by the admin
            List<Product> adminProducts = adminProductService.getAdminProducts(adminId);
            return ResponseEntity.ok(adminProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve admin products: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/delete_admin_product")
    public ResponseEntity<Object> deleteAdminProduct(@RequestParam String adminId, @RequestParam String productId) {
        try {
            adminProductService.deleteAdminProduct(adminId, productId);
            return ResponseEntity.ok("Product deleted from admin's list and repository");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete admin product: " + e.getMessage());
        }
    }
    

}
