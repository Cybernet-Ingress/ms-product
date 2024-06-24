package az.javidan.msproduct.dao.repository;

import az.javidan.msproduct.dao.entity.ProductEntity;
import az.javidan.msproduct.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByIdAndStatusNot(Long id, ProductStatus status);

    Page<ProductEntity> findAll(Specification<ProductEntity> spec, Pageable pageable);
}
