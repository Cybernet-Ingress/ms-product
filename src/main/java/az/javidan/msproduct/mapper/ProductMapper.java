package az.javidan.msproduct.mapper;

import az.javidan.msproduct.dao.entity.ProductEntity;
import az.javidan.msproduct.model.enums.ProductStatus;
import az.javidan.msproduct.model.request.CreateProductRequest;
import az.javidan.msproduct.model.response.PageableResponse;
import az.javidan.msproduct.model.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductMapper {

    PRODUCT_MAPPER;


    public ProductEntity buildProductEntity(CreateProductRequest product) {
        return ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(ProductStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .quantity(product.getQuantity())
                .subscribe(product.getSubscribe())
                .categoryId(product.getCategoryId())
                .userId(product.getUserId())
                .build();

    }

    public ProductResponse buildProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .rating(product.getRating())
                .quantity(product.getQuantity())
                .build();
    }

    public PageableResponse<ProductResponse> buildPageableProductResponse(Page<ProductEntity> productPages) {
        return PageableResponse.<ProductResponse>builder()
                .data(productPages.getContent().stream().map(PRODUCT_MAPPER::buildProductResponse)
                        .collect(Collectors.toList()))
                .hasNextPage(productPages.hasNext())
                .lastPageNumber(productPages.getTotalPages())
                .totalElements(productPages.getTotalElements())
                .build();
    }
}
