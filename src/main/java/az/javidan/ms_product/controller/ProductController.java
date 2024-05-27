package az.javidan.ms_product.controller;


import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.response.ProductResponse;
import az.javidan.ms_product.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CreateProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

//    @GetMapping
//    public List<ProductEntity> getAllProducts(){
//        return productService.
//    }

}
