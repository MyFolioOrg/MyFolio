package me.may.myfolio.common.messaging;

import me.may.myfolio.common.messaging.event.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public abstract class EventHandler<T extends Event> {
    protected abstract void receive(String eventJson);
}
