package az.javidan.ms_product.service.abstraction;

import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct (CreateProductRequest request);
    ProductResponse getProduct (Long id);

    void deleteProduct (Long id);

    void updateProduct (Long id, String name, String description, Double price);

//    List<ProductResponse> getProductList();
}
