package com.nguyenminh.testingmicroservice.service;


import com.nguyenminh.testingmicroservice.dto.ProductRequest;
import com.nguyenminh.testingmicroservice.dto.ProductResponse;
import com.nguyenminh.testingmicroservice.model.Product;
import com.nguyenminh.testingmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    // create object of repository
    private final ProductRepository productRepository;


    // createProduct method
    // take parameter of object of Product request
    public void createProduct(ProductRequest productRequest){
        //create product object of request to product
        Product product = Product.builder()
                //get the name of request from java object
                .name(productRequest.getName())
                //get the description of request from java object
                .description(productRequest.getDescription())
                //get the price of request from java object
                .price(productRequest.getPrice())
                .build();
        // save product object
        productRepository.save(product);

        // give log
        log.info("Product {} is save", product.getId());
    }


    // get all method, give all product response by list
    public List<ProductResponse> getAllProduct() {
        // find all and put in products
        List<Product> products = productRepository.findAll();


        return products.stream().map(this::mapToProductResponse).toList();

    }


    // return ProductResponse type , pass Product object into method
    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
