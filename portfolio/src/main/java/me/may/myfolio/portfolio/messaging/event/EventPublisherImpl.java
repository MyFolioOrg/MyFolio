package me.may.myfolio.portfolio.messaging.event;

import me.may.myfolio.common.messaging.EventPublisher;
import me.may.myfolio.common.messaging.event.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class EventPublisherImpl<T extends Event> implements EventPublisher<T> {
    private static final String ROUTING_KEY = "create.portfolio";
    private final RabbitTemplate template;
    public EventPublisherImpl(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void doPublish(String eventJson) throws IOException, TimeoutException {
        template.convertAndSend(ROUTING_KEY, eventJson);
    }

}
