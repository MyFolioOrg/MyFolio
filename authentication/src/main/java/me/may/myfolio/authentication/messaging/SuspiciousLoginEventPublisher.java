package me.may.myfolio.authentication.messaging;

import me.may.myfolio.common.messaging.AbstractEventPublisher;
import me.may.myfolio.common.messaging.event.SuspiciousLoginEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SuspiciousLoginEventPublisher extends AbstractEventPublisher<SuspiciousLoginEvent> {
    public SuspiciousLoginEventPublisher(RabbitTemplate template) {
        super(template, "login.suspicious");
    }
}
