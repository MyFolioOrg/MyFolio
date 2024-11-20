package me.may.myfolio.fileupload.messaging;

import me.may.myfolio.common.messaging.EventHandler;
import me.may.myfolio.common.messaging.event.Event;
import me.may.myfolio.common.messaging.event.PortfolioCreationEvent;
import me.may.myfolio.fileupload.service.FileService;
import me.may.myfolio.fileupload.service.impl.FileServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PortfolioCreationEventHandler extends EventHandler<PortfolioCreationEvent> {
    private final FileService fileService;
    public PortfolioCreationEventHandler(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

 @Override
    @RabbitListener(queues = "portfolio-file-uploads")
    public void receive(String eventJson) {
        Optional<PortfolioCreationEvent> optionalEvent = (Optional<PortfolioCreationEvent>) Event.fromJson(eventJson, PortfolioCreationEvent.class);
        optionalEvent.ifPresent(e -> {
            fileService.upload(e.content(), e.id() + ".md");
        });
    }
}
