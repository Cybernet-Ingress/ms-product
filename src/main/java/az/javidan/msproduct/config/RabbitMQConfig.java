package az.javidan.msproduct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String ratingQ;
    private final String ratingDLQ;
    private final String ratingQExchange;
    private final String ratingDLQExchange;
    private final String ratingQKey;
    private final String ratingDLQKey;
    private final String categoryQ;
    private final String categoryDLQ;
    private final String categoryQExchange;
    private final String categoryDLQExchange;
    private final String categoryQKey;
    private final String categoryDLQKey;


    public RabbitMQConfig(@Value("${spring.rabbitmq.queue.rating}") String ratingQ,
                          @Value("${spring.rabbitmq.queue.rating-dlq}") String ratingDLQ,
                          @Value("${spring.rabbitmq.queue.category}") String categoryQ,
                          @Value("${spring.rabbitmq.queue.category-dlq}") String categoryDLQ) {
        this.ratingQ = ratingQ;
        this.ratingDLQ = ratingDLQ;
        this.ratingQExchange = ratingQ + "_Exchange";
        this.ratingDLQExchange = ratingDLQ + "_Exchange";
        this.ratingQKey = ratingQ + "_Key";
        this.ratingDLQKey = ratingDLQ + "_Key";
        this.categoryQ = categoryQ;
        this.categoryDLQ = categoryDLQ;
        this.categoryQExchange = categoryQ + "_Exchange";
        this.categoryDLQExchange = categoryDLQ + "_Exchange";
        this.categoryQKey = categoryQ + "_Key";
        this.categoryDLQKey = categoryDLQ + "_Key";
    }

    @Bean
    public DirectExchange ratingDLQExchange() {
        return new DirectExchange(ratingDLQExchange);
    }

    @Bean
    public DirectExchange ratingQExchange() {
        return new DirectExchange(ratingQExchange);
    }

    @Bean
    public Queue ratingDQL() {
        return new Queue(ratingDLQ, true);
    }

    @Bean
    public Queue ratingQ() {
        return new Queue(ratingQ, true);
    }

    @Bean
    public Binding ratingDLQBinding() {
        return BindingBuilder.bind(ratingDQL())
                .to(ratingDLQExchange())
                .with(ratingDLQKey);
    }

    @Bean
    public Binding ratingQBinding() {
        return BindingBuilder.bind(ratingQ())
                .to(ratingQExchange())
                .with(ratingQKey);
    }

    @Bean
    public DirectExchange categoryDLQExchange() {
        return new DirectExchange(categoryDLQExchange);
    }

    @Bean
    public DirectExchange categoryQExchange() {
        return new DirectExchange(categoryQExchange);
    }

    @Bean
    public Queue categoryDQL() {
        return new Queue(categoryDLQ, true);
    }

    @Bean
    public Queue categoryQ() {
        return new Queue(categoryQ, true);
    }

    @Bean
    public Binding categoryDLQBinding() {
        return BindingBuilder.bind(categoryDQL())
                .to(categoryDLQExchange())
                .with(categoryDLQKey);
    }

    @Bean
    public Binding categoryQBinding() {
        return BindingBuilder.bind(categoryQ())
                .to(categoryQExchange())
                .with(categoryQKey);
    }
}
