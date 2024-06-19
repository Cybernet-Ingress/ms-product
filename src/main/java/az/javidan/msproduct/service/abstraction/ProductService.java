package az.javidan.msproduct.service.abstraction;

import az.javidan.msproduct.model.request.CreateProductRequest;
import az.javidan.msproduct.model.request.PageCriteria;
import az.javidan.msproduct.model.request.ProductCriteria;
import az.javidan.msproduct.model.request.ProductUpdateRequestDto;
import az.javidan.msproduct.model.response.PageableResponse;
import az.javidan.msproduct.model.response.ProductResponse;

import java.math.BigDecimal;

public interface ProductService {

    void createProduct(CreateProductRequest request);

    ProductResponse getProduct(Long id);

    void deleteProduct(Long id);

    void updateProduct(Long id, ProductUpdateRequestDto productUpdateDto);

    PageableResponse<ProductResponse> getAllProducts(ProductCriteria productCriteria,
                                                     PageCriteria pageCriteria);


}
