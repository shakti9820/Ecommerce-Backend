package com.bootspring.ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Entity.Product;
import com.bootspring.ecommerce.Repository.ProductRepository;
import com.bootspring.ecommerce.Repository.SearchProductRepository;

@Service
public class ProductService {
	
    private final ProductRepository productRepository;
    private final SearchProductRepository searchProductRepository;
    
     @Autowired
	public ProductService(ProductRepository productRepository,SearchProductRepository searchProductRepository) {
		this.productRepository = productRepository;
		this.searchProductRepository=searchProductRepository;
	}
     public List<Product> getAllProducts() {
         return productRepository.findAll();
     }
	public Product getProductById(String productId) {
		return productRepository.getProductById(productId);
	}
	public List<Product> getProductByText(String text) {
		return searchProductRepository.findByText(text);
	}
    
}
