package com.nguyenminh.testingmicroservice.repository;

import com.nguyenminh.testingmicroservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
