package az.javidan.ms_product.model.request;

import az.javidan.ms_product.model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteria {
    private String name;
    private String description;
    private Double priceFrom;
    private Double priceTo;
    private ProductStatus status;
    private Double rating;
}
