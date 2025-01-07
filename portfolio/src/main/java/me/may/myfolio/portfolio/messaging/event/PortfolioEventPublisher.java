package me.may.myfolio.portfolio.messaging.event;

import me.may.myfolio.common.messaging.AbstractEventPublisher;
import me.may.myfolio.common.messaging.event.PortfolioCreationEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PortfolioEventPublisher extends AbstractEventPublisher<PortfolioCreationEvent> {
    public PortfolioEventPublisher(RabbitTemplate template) {
        super(template, "create.portfolio");
    }
}
