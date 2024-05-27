package az.javidan.ms_product.service.concrete;

import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.dao.repository.ProductRepository;
import az.javidan.ms_product.exception.NotFoundException;
import az.javidan.ms_product.model.request.CreateProductRequest;
import az.javidan.ms_product.model.response.ProductResponse;
import az.javidan.ms_product.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.javidan.ms_product.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.javidan.ms_product.model.enums.ProductStatus.DELETED;


@Service
@RequiredArgsConstructor
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
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, String name, String description, Double price) {
        var product = fetchProductIfExist(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepository.save(product);
    }

//    @Override
//    public List<ProductResponse> getProductList() {
//        return productRepository.findByIdAndStatusNot();
//    }


    private ProductEntity fetchProductIfExist(Long id) {
        return productRepository.findByIdAndStatusNot(id,DELETED)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
