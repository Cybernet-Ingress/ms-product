package az.javidan.ms_product.dao.repository;

import az.javidan.ms_product.dao.entity.ProductEntity;
import az.javidan.ms_product.model.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> , JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByIdAndStatusNot(Long id, ProductStatus status);

}
