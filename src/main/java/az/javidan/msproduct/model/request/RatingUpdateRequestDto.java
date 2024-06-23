package az.javidan.msproduct.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingUpdateRequestDto {
    private Long id;
    private BigDecimal rating;
}
