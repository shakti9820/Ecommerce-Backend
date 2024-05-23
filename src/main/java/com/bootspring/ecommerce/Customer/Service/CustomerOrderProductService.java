package com.bootspring.ecommerce.Customer.Service;


import com.bootspring.ecommerce.Customer.Entity.CustomerCartProduct;
import com.bootspring.ecommerce.Customer.Entity.CustomerOrderProduct;
import com.bootspring.ecommerce.Customer.Repository.CustomerOrderProductRepository;
import com.bootspring.ecommerce.Entity.Product;
import com.bootspring.ecommerce.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderProductService {

    private final CustomerOrderProductRepository customerOrderProductRepository;
    private final ProductService productService;

    @Autowired
    public CustomerOrderProductService(CustomerOrderProductRepository customerOrderProductRepository,ProductService productService) {
        this.customerOrderProductRepository = customerOrderProductRepository;
        this.productService=productService;
    }

    public Product addProduct(Product product, String customerId) {

    	CustomerOrderProduct customerOrderProduct = customerOrderProductRepository.findByCustomerId(customerId);
        if (customerOrderProduct == null) {
        	customerOrderProduct = new CustomerOrderProduct();
        	customerOrderProduct.setCustomerId(customerId);
        	customerOrderProduct.setProductIds(new ArrayList<String>());
        }
        customerOrderProduct.getProductIds().add(product.getId());
        customerOrderProductRepository.save(customerOrderProduct);

        return product;
    }

	    public List<Product> getOrderProducts(String customerId) {
        CustomerOrderProduct orderProduct = customerOrderProductRepository.findByCustomerId(customerId);
        if (orderProduct != null) {
           
            List<String> productIds = orderProduct.getProductIds();

            List<Product> products = new ArrayList<>();

            for (String productId : productIds) {
          
                Product product = productService.getProductById(productId);
              
                if (product != null) {
                    products.add(product);
                }
            }

            return products;
        } else {
            return new ArrayList<>();
        }
    }

		  public void deleteCartProduct(String customerId, String productId) {
		// Retrieve the customer document by customer ID
    		CustomerOrderProduct customerOrder = customerOrderProductRepository.findByCustomerId(customerId);
        
	        if (customerOrder != null) {
	            // Remove the productId from the list of productIds in the customer's cart
	        	customerOrder.getProductIds().remove(productId);
	            
	            // Save the updated customer cart document back to the database
	        	customerOrderProductRepository.save(customerOrder);
	        } else {
	            throw new RuntimeException("Customer order not found with ID: " + customerId);
	        }
		
	}

		  public void placeOrder(String userId, String productId) {
		        // Retrieve or create CustomerOrderProduct entity for the user
		        CustomerOrderProduct customerOrderProduct = customerOrderProductRepository.findByCustomerId(userId);
		        if (customerOrderProduct == null) {
		            customerOrderProduct = new CustomerOrderProduct(userId, new ArrayList<>());
		        }
		        
		        // Add the productId to the list of ordered products
		        customerOrderProduct.getProductIds().add(productId);
		        
		        // Save the updated CustomerOrderProduct entity
		        customerOrderProductRepository.save(customerOrderProduct);
		    }


    
}
