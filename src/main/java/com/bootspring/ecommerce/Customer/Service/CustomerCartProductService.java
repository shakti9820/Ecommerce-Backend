package com.bootspring.ecommerce.Customer.Service;


import com.bootspring.ecommerce.Customer.Entity.CustomerCartProduct;
import com.bootspring.ecommerce.Customer.Entity.CustomerOrderProduct;
import com.bootspring.ecommerce.Customer.Entity.CustomerUser;
import com.bootspring.ecommerce.Customer.Repository.CustomerCartProductRepository;
import com.bootspring.ecommerce.Customer.Repository.CustomerOrderProductRepository;
import com.bootspring.ecommerce.Entity.Product;
import com.bootspring.ecommerce.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerCartProductService {

    private final CustomerCartProductRepository customerCartProductRepository;
    private final CustomerOrderProductRepository customerOrderProductRepository;
    private final ProductService productService;

    @Autowired
    public CustomerCartProductService(CustomerCartProductRepository customerCartProductRepository,ProductService productService,CustomerOrderProductRepository customerOrderProductRepository) {
        this.customerCartProductRepository = customerCartProductRepository;
		this.customerOrderProductRepository = customerOrderProductRepository;
        this.productService = productService;
    }
    

    public Product addProduct(Product product, String customerId) {

        CustomerCartProduct customerCartProduct = customerCartProductRepository.findByCustomerId(customerId);
        if (customerCartProduct == null) {
        	customerCartProduct = new CustomerCartProduct();
        	customerCartProduct.setCustomerId(customerId);
        	customerCartProduct.setProductIds(new ArrayList<String>());
        }
        customerCartProduct.getProductIds().add(product.getId());
        customerCartProductRepository.save(customerCartProduct);

        return product;
    }
    

    public List<Product> getCartProducts(String customerId) {
        CustomerCartProduct cartProduct = customerCartProductRepository.findByCustomerId(customerId);
        if (cartProduct != null) {
           
            List<String> productIds = cartProduct.getProductIds();

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
    		CustomerCartProduct customerCart = customerCartProductRepository.findByCustomerId(customerId);
        
	        if (customerCart != null) {
	            // Remove the productId from the list of productIds in the customer's cart
	            customerCart.getProductIds().remove(productId);
	            
	            // Save the updated customer cart document back to the database
	            customerCartProductRepository.save(customerCart);
	        } else {
	            throw new RuntimeException("Customer cart not found with ID: " + customerId);
	        }
		
	}


	public void cartToOrder(String customerId, String productId) {
		 CustomerCartProduct customerCart = customerCartProductRepository.findByCustomerId(customerId);
	       
	        
	        if (customerCart != null) {
	            // Remove the productId from the list of productIds in the customer's cart
	            customerCart.getProductIds().remove(productId);
	            
	            // Save the updated customer cart document back to the database
	            customerCartProductRepository.save(customerCart);
	            CustomerOrderProduct customerOrderProduct = customerOrderProductRepository.findByCustomerId(customerId);
	            if (customerOrderProduct == null) {
	            	customerOrderProduct = new CustomerOrderProduct();
	            	customerOrderProduct.setCustomerId(customerId);
	            	customerOrderProduct.setProductIds(new ArrayList<String>());
	            }
	            customerOrderProduct.getProductIds().add(productId);
	            customerOrderProductRepository.save(customerOrderProduct);
	        } 
	         else {
	            throw new RuntimeException("Customer cart not found with ID: " + customerId);
	        }
	            
	            
	}


	





	
	

    
}
