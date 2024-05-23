package com.bootspring.ecommerce.Customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bootspring.ecommerce.Customer.Service.CustomerCartProductService;
import com.bootspring.ecommerce.Entity.Product;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class CustomerCartProductController {
      private final CustomerCartProductService customerCartProductService;
    
    @Autowired
    public CustomerCartProductController(CustomerCartProductService customerCartProductService) {
        this.customerCartProductService = customerCartProductService;
    }

    @PostMapping("/add_to_cart")
    public ResponseEntity<Object> addProduct(@RequestBody Product product, @RequestParam String customerId) {
//    	System.out.println(product.getId());
    	
        try {
            // Add the product to the database and map it to the admin
            Product savedProduct = customerCartProductService.addProduct(product, customerId);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product: " + e.getMessage());
        }
    }
    
    @GetMapping("/get_cart_products")
    public ResponseEntity<Object> getCartProducts(@RequestParam String customerId) {
//    	System.out.println(customerId);
        try {
            // Retrieve the list of products associated with the customer ID
            List<Product> cartProducts = customerCartProductService.getCartProducts(customerId);
            return ResponseEntity.ok(cartProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve cart products: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/delete_cart_product")
    public ResponseEntity<Object> deleteCartProduct(@RequestParam String customerId, @RequestParam String productId) {
//    	System.out.println(customerId);
//    	System.out.println(productId);
        try {
            // Delete the product from the cart based on the customer ID and product ID
            customerCartProductService.deleteCartProduct(customerId, productId);
            return ResponseEntity.ok("Product deleted from cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product from cart: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/cart_to_order")
    public ResponseEntity<Object> cartToOrder(@RequestParam String customerId, @RequestParam String productId) {
    	System.out.println(customerId);
    	System.out.println(productId);
        try {
            customerCartProductService.cartToOrder(customerId, productId);
            return ResponseEntity.ok("Product moved from cart to order");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to move product from cart to order: " + e.getMessage());
        }
    }
    
}
