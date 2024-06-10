package az.javidan.ms_product.mapper;

import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.dao.repository.ProductRepository;
import az.javidan.ms_product.model.enums.ProductStatus;
import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.response.ProductResponse;

import java.time.LocalDateTime;

public enum ProductMapper {

    PRODUCT_MAPPER;


    public ProductEntity buildProductEntity(CreateProductRequest product) {
        return  ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(ProductStatus.CREATED)
                .created_at(LocalDateTime.now())
                .build();

    }

    public ProductResponse buildProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .rating(product.getRating())
                .build();
    }
}
