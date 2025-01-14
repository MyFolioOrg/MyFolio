package me.may.myfolio.fileupload.messaging;

import me.may.myfolio.common.messaging.EventHandler;
import me.may.myfolio.common.messaging.event.Event;
import me.may.myfolio.common.messaging.event.PortfolioCreationEvent;
import me.may.myfolio.fileupload.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

@Component
public class PortfolioCreationEventHandler extends EventHandler<PortfolioCreationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioCreationEventHandler.class);

    private final FileService fileService;

    public PortfolioCreationEventHandler(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    @RabbitListener(queues = "portfolio-file-uploads")
    public void receive(String eventJson) {
        Optional<PortfolioCreationEvent> optionalEvent = (Optional<PortfolioCreationEvent>) Event.fromJson(eventJson, PortfolioCreationEvent.class);
        optionalEvent.ifPresentOrElse(
                e -> fileService.upload(e.content(), e.id() + ".md"),
                () -> logger.error("An incoming portfolio creation event could not be parsed. Can't save it."));

    }
}
