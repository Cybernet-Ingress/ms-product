package az.javidan.ms_product.service.concrete;

import az.javidan.ms_product.annotation.Log;
import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.dao.repository.ProductRepository;
import az.javidan.ms_product.exception.NotFoundException;
import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.request.PageCriteria;
import az.javidan.ms_product.model.request.ProductCriteria;
import az.javidan.ms_product.model.response.PageableResponse;
import az.javidan.ms_product.model.response.ProductResponse;
import az.javidan.ms_product.service.abstraction.ProductService;
import az.javidan.ms_product.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static az.javidan.ms_product.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.javidan.ms_product.model.enums.ProductStatus.DELETED;


@Service
@RequiredArgsConstructor
@Log
public class ProductServiceHandler implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductRequest request) {
        productRepository.save(PRODUCT_MAPPER.buildProductEntity(request));
    }

    @Override
    public ProductResponse getProduct(Long id) {
        var product = fetchProductIfExist(id);
        return PRODUCT_MAPPER.buildProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        var product = fetchProductIfExist(id);
        product.setStatus(DELETED);
        product.setDeleted_at(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, String name, String description, Double price) {
        var product = fetchProductIfExist(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setModified_at(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public PageableResponse<ProductResponse> getAllProducts(ProductCriteria productCriteria,
                                                            PageCriteria pageCriteria) {

        var pageRequest =
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending());
        var productPage = productRepository.findAll(new ProductSpecification(productCriteria), pageRequest);

        return PageableResponse.<ProductResponse>builder()
                .data(productPage.getContent().stream().map(PRODUCT_MAPPER::buildProductResponse)
                        .collect(Collectors.toList()))
                .hasNextPage(productPage.hasNext())
                .lastPageNumber(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .build();
    }

    @Override
    public void updateRating(Long id, Double rating) {
        var product = fetchProductIfExist(id);
        product.setRating(rating);
        product.setModified_at(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public void updateComment(Long id, String comment) {
        var product = fetchProductIfExist(id);
        product.setComment(comment);
        product.setModified_at(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public void updateCategory(Long id, String categoryId) {
        var product = fetchProductIfExist(id);
        product.setCategory_id(categoryId);
        product.setModified_at(LocalDateTime.now());
        productRepository.save(product);
    }


    private ProductEntity fetchProductIfExist(Long id) {
        return productRepository.findByIdAndStatusNot(id, DELETED)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
