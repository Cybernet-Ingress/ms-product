package az.javidan.ms_product.service.abstraction;

import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.request.PageCriteria;
import az.javidan.ms_product.model.request.ProductCriteria;
import az.javidan.ms_product.model.response.PageableResponse;
import az.javidan.ms_product.model.response.ProductResponse;

public interface ProductService {

    void createProduct(CreateProductRequest request);

    ProductResponse getProduct(Long id);

    void deleteProduct(Long id);

    void updateProduct(Long id, String name, String description, Double price);

    PageableResponse<ProductResponse> getAllProducts(ProductCriteria productCriteria,
                                                     PageCriteria pageCriteria);

    void updateRating(Long id, Double rating);

    void updateComment(Long id, String comment);
    void updateCategory(Long id, String categoryId);

}
