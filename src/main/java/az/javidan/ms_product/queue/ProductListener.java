package az.javidan.ms_product.queue;

import az.javidan.ms_product.annotation.Log;
import az.javidan.ms_product.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Log
public class ProductListener {
    private final ProductService productService;

    @RabbitListener(queues = "${rabbitmq.queue.rating}")
    public void receiveRating(Long id, Double rating) {
        try {
            productService.updateRating(id, rating);
        } catch (Exception e) {
            log.error("ActionLog.receiveRating.error:  {}", id, e);
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.comment}")
    public void receiveComment(Long id, String comment) {
        try {
            productService.updateComment(id, comment);
        } catch (Exception e) {
            log.error("ActionLog.receiveRating.error:  {}", id, e);
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.category}")
    public void receiveCategory(Long id, String categoryId) {
        try {
            productService.updateCategory(id, categoryId);
        } catch (Exception e) {
            log.error("ActionLog.receiveRating.error:  {}", id, e);
        }
    }
}
