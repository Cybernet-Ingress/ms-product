package az.javidan.ms_product.dao.entity;

import az.javidan.ms_product.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
@Builder
@DynamicUpdate
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category_id;
    @Enumerated(STRING)
    private ProductStatus status;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private LocalDateTime deleted_at;
    private Double rating;
    private String comment;
}
