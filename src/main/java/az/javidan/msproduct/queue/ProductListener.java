package az.javidan.msproduct.queue;

import az.javidan.msproduct.model.request.RatingUpdateRequestDto;
import az.javidan.msproduct.model.request.SubscribeUpdateRequestDto;
import az.javidan.msproduct.service.abstraction.RatingService;
import az.javidan.msproduct.service.abstraction.SubscribeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class ProductListener {
    private final RatingService ratingService;
    private final SubscribeService subscribeService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${spring.rabbitmq.queue.rating}")
    public void receiveRating(String data) {
        log.info("ActionLog.receiveRating.start message: {}", data);
        try {
            RatingUpdateRequestDto ratingUpdateRequestDto = objectMapper.readValue(data, RatingUpdateRequestDto.class);
            ratingService.updateRating(ratingUpdateRequestDto.getId(), ratingUpdateRequestDto.getRating());
        } catch (Exception e) {
            log.error("ActionLog.receiveRating.error: {}", data, e);
        }
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.subscribe}")
    public void receiveSubscribe(String data) {
            log.info("ActionLog.receiveSubscribe.start message: {}", data);
            try {
                SubscribeUpdateRequestDto subscribeUpdateRequestDto = objectMapper.readValue(data, SubscribeUpdateRequestDto.class);
                subscribeService.updateSubscribe(subscribeUpdateRequestDto.getId(), subscribeUpdateRequestDto.getSubscribe());
            } catch (Exception e) {
                log.error("ActionLog.receiveSubscribe.error: {}", data, e);
            }
    }



}
