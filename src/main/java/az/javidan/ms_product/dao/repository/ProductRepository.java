package az.javidan.ms_product.dao.repository;

import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.model.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByIdAndStatusNot(Long id, ProductStatus status);
}
