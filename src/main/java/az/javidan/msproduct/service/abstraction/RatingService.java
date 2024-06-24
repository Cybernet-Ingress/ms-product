package az.javidan.msproduct.service.abstraction;

import java.math.BigDecimal;

public interface RatingService {
    void updateRating(Long id, BigDecimal rating);
}
