package com.bootspring.ecommerce.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.bootspring.ecommerce.Entity.Product;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchProductRepositoryImp implements SearchProductRepository{
	@Autowired
	MongoClient client;
    @Autowired
	MongoConverter mongoConverter;
	
	public List<Product> findByText(String text) {

       List<Product> list=new ArrayList<>();
       
       
       MongoDatabase database = client.getDatabase("Ecommerce");
       MongoCollection<Document> collection = database.getCollection("Products");
       AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
           new Document("text", 
           new Document("query", text)
                       .append("path", Arrays.asList("name", "highlights", "description")))), 
           new Document("$limit", 8L)));

       result.forEach(doc-> list.add(mongoConverter.read(Product.class, doc)));
       
		return list;
	}

        
}
