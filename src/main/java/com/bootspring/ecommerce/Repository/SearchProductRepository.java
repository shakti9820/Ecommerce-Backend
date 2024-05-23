package com.bootspring.ecommerce.Repository;

import java.util.List;

import com.bootspring.ecommerce.Entity.Product;

public interface SearchProductRepository {

	List<Product> findByText(String text);
}
