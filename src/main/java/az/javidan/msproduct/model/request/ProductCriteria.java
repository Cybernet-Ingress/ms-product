package az.javidan.msproduct.model.request;

import az.javidan.msproduct.model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
