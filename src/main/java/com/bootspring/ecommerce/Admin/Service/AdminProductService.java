package com.bootspring.ecommerce.Admin.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootspring.ecommerce.Admin.Entity.AdminProduct;
import com.bootspring.ecommerce.Admin.Repository.AdminProductRepository;
import com.bootspring.ecommerce.Entity.Product;
import com.bootspring.ecommerce.Repository.ProductRepository;
import com.bootspring.ecommerce.Service.ProductService;

@Service
public class AdminProductService {
      private final AdminProductRepository adminProductRepository;
      private final ProductRepository productRepository;
      private final ProductService productService;
        @Autowired
		public AdminProductService(AdminProductRepository adminProductRepository, ProductRepository productRepository,ProductService productService) {
			
			this.adminProductRepository = adminProductRepository;
			this.productRepository = productRepository;
			this.productService=productService;
		}
      
        public Product addProduct(Product product, String adminId) {
            Product savedProduct = productRepository.save(product);

            AdminProduct adminProduct = adminProductRepository.findByAdminId(adminId);
            if (adminProduct == null) {
                adminProduct = new AdminProduct();
                adminProduct.setAdminId(adminId);
                adminProduct.setProductIds(new ArrayList<String>());
            }
            adminProduct.getProductIds().add(savedProduct.getId());
            adminProductRepository.save(adminProduct);

            return savedProduct;
        }

		 public List<Product> getAdminProducts(String adminId) {
        AdminProduct adminProduct = adminProductRepository.findByAdminId(adminId);
        if (adminProduct != null) {
           
            List<String> productIds = adminProduct.getProductIds();

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

		public void deleteAdminProduct(String adminId, String productId) {
			AdminProduct adminProduct = adminProductRepository.findByAdminId(adminId);
	        if (adminProduct != null) {
	            adminProduct.getProductIds().remove(productId);
	            adminProductRepository.save(adminProduct);
	            
	            // Delete the product from the product repository
	            productRepository.deleteById(productId);
	        } else {
	            throw new RuntimeException("Admin product not found with ID: " + adminId);
	        }
			
		}
        
        
      
      
}
