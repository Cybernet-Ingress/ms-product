package az.javidan.msproduct.service.specification;

import az.javidan.msproduct.dao.entity.ProductEntity;
import az.javidan.msproduct.model.request.ProductCriteria;
import az.javidan.msproduct.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
@Data
public class ProductSpecification implements Specification<ProductEntity> {

    private ProductCriteria productCriteria;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        var predicates = PredicateUtil.builder()
                .addNullSafety(productCriteria.getName(),
                        name -> criteriaBuilder.like(root.get("name"), applyLikePattern(name)))
                .addNullSafety(productCriteria.getDescription(),
                        description -> criteriaBuilder.like(root.get("description"), applyLikePattern(description)))
                .addNullSafety(productCriteria.getStatus(),
                        productStatus -> criteriaBuilder.equal(root.get("status"), productStatus))
                .addNullSafety(productCriteria.getPriceFrom(),
                        priceFrom -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceFrom))
                .addNullSafety(productCriteria.getPriceTo(),
                        priceTo -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceTo))
                .addNullSafety(productCriteria.getRating(),
                        rating -> criteriaBuilder.equal(root.get("rating"),rating))
                .build();
        return criteriaBuilder.and(predicates);
    }

    private String applyLikePattern(String field){
        return "%" + field + "%";
    }
}
