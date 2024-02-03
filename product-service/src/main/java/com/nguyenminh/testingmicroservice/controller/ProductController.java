package com.nguyenminh.testingmicroservice.controller;

import com.nguyenminh.testingmicroservice.dto.ProductRequest;
import com.nguyenminh.testingmicroservice.dto.ProductResponse;
import com.nguyenminh.testingmicroservice.model.Product;
import com.nguyenminh.testingmicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    // define object of service
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // return created response when object is created
    // @RequestBody : json -> java type object
    // @ResponseBody : java object -> json
    // @RestController include  @ResponseBody
    public void createProduct(@RequestBody ProductRequest productRequest){
        //use createProduct method
        productService.createProduct(productRequest);
    }




    @GetMapping
    @ResponseStatus(HttpStatus.OK) // return 202 ok

    //A give list of ProductResponse
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProduct();
    }

}


