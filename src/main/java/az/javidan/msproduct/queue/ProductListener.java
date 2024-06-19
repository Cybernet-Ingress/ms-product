package az.javidan.msproduct.queue;

import az.javidan.msproduct.service.abstraction.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductListener {
    private final RatingService ratingService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.rating}")
    public void receiveRating(Long id, BigDecimal rating) {
        try {
            ratingService.updateRating(id, rating);
        } catch (Exception e) {
            log.error("ActionLog.receiveRating.error:  {}", id, e);
        }
    }
}
