package az.javidan.msproduct.service.concrete;

import az.javidan.msproduct.annotation.Log;
import az.javidan.msproduct.dao.entity.ProductEntity;
import az.javidan.msproduct.dao.repository.ProductRepository;
import az.javidan.msproduct.exception.NotFoundException;
import az.javidan.msproduct.service.abstraction.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static az.javidan.msproduct.exception.ExceptionConstants.PRODUCT_NOT_FOUND_CODE;
import static az.javidan.msproduct.exception.ExceptionConstants.PRODUCT_NOT_FOUND_MESSAGE;
import static az.javidan.msproduct.model.enums.ProductStatus.DELETED;


@Service
@RequiredArgsConstructor
@Log
public class SubscribeServiceHandler implements SubscribeService {
    private final ProductRepository productRepository;

    @Override
    public void updateSubscribe(Long id, Boolean subscribe) {
        var product = fetchProductIfExist(id);
        product.setSubscribe(subscribe);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    private ProductEntity fetchProductIfExist(Long id) {
        return productRepository.findByIdAndStatusNot(id, DELETED)
                .orElseThrow(() -> new NotFoundException(
                        String.format(PRODUCT_NOT_FOUND_MESSAGE, id),
                        PRODUCT_NOT_FOUND_CODE
                ));
    }
}
