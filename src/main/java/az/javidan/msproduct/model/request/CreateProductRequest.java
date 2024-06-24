package az.javidan.msproduct.model.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {
    @NotNull(message = "The name is required.")
    @NotBlank
    private String name;

    @NotNull(message = "The description is required.")
    @NotBlank
    private String description;

    @NotNull(message = "The price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "The quantity is required.")
    @Positive(message = "The quantity must be a positive number.")
    private Long quantity;

    @NotNull(message = "The category ID is required.")
    @Positive(message = "The category ID must be a positive number.")
    private Long categoryId;

    @NotNull(message = "The user ID is required.")
    @Positive(message = "The user ID must be a positive number.")
    private Long userId;
}
