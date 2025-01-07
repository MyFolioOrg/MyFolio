package me.may.myfolio.common.messaging;

import me.may.myfolio.common.messaging.event.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractEventPublisher<T extends Event> implements EventPublisher<T> {
    private final String routingKey;
    private final RabbitTemplate template;
    public AbstractEventPublisher(RabbitTemplate template, String routingKey) {
        this.template = template;
        this.routingKey = routingKey;
    }

    @Override
    public void publish(String eventJson) throws IOException, TimeoutException {
        template.convertAndSend(routingKey, eventJson);
    }

}
