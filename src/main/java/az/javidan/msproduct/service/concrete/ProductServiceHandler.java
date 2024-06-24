package az.javidan.msproduct.service.concrete;

import az.javidan.msproduct.annotation.Log;
import az.javidan.msproduct.dao.entity.ProductEntity;
import az.javidan.msproduct.dao.repository.ProductRepository;
import az.javidan.msproduct.exception.NotFoundException;
import az.javidan.msproduct.model.request.CreateProductRequest;
import az.javidan.msproduct.model.request.PageCriteria;
import az.javidan.msproduct.model.request.ProductCriteria;
import az.javidan.msproduct.model.request.ProductUpdateRequestDto;
import az.javidan.msproduct.model.response.PageableResponse;
import az.javidan.msproduct.model.response.ProductResponse;
import az.javidan.msproduct.service.abstraction.ProductService;
import az.javidan.msproduct.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static az.javidan.msproduct.exception.ExceptionConstants.PRODUCT_NOT_FOUND_CODE;
import static az.javidan.msproduct.exception.ExceptionConstants.PRODUCT_NOT_FOUND_MESSAGE;
import static az.javidan.msproduct.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.javidan.msproduct.model.enums.ProductStatus.DELETED;


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
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        var product = fetchProductIfExist(id);
        product.setName(productUpdateRequestDto.getName());
        product.setDescription(productUpdateRequestDto.getDescription());
        product.setPrice(productUpdateRequestDto.getPrice());
        product.setUpdatedAt(LocalDateTime.now());
        product.setSubscribe(productUpdateRequestDto.getSubscribe());
        productRepository.save(product);
    }

    @Override
    public PageableResponse<ProductResponse> getAllProducts(ProductCriteria productCriteria, PageCriteria pageCriteria) {

        Sort sort = Sort.by(Sort.Order.desc("subscribe"));

        if (pageCriteria.getSortBy() != null && pageCriteria.getSortDirection() != null) {
            Sort.Direction direction = Sort.Direction.fromString(pageCriteria.getSortDirection());
            sort = sort.and(Sort.by(direction, pageCriteria.getSortBy()));
        } else {
            sort = sort.and(Sort.by(Sort.Direction.DESC, "id"));
        }

        var pageRequest = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), sort);
        var productPage = productRepository.findAll(new ProductSpecification(productCriteria), pageRequest);

        return PRODUCT_MAPPER.buildPageableProductResponse(productPage);
    }


    private ProductEntity fetchProductIfExist(Long id) {
        return productRepository.findByIdAndStatusNot(id, DELETED)
                .orElseThrow(() -> new NotFoundException(
                        String.format(PRODUCT_NOT_FOUND_MESSAGE, id),
                        PRODUCT_NOT_FOUND_CODE
                ));
    }
}
