package me.may.myfolio.fileupload.messaging;

import me.may.myfolio.common.messaging.event.PortfolioCreationEvent;
import me.may.myfolio.fileupload.service.FileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
public class EventHandlingTests {
    @InjectMocks
    private PortfolioCreationEventHandler portfolioCreationEventHandler;
    @Mock
    private FileService fileService;

    @Test
    void handleValidPortfolioCreationEvent() {
        // Arrange
        String incomingEvent =
                new PortfolioCreationEvent(
                        "Test Portfolio",
                        1,
                        123,
                        "Test portfolio's contents."
                ).toJson();

        // Act
        portfolioCreationEventHandler.receive(incomingEvent);

        // Assert
        verify(fileService, times(1))
                .upload("Test portfolio's contents.", "1.md");
    }

    @Test
    void handleInvalidPortfolioCreationEvent() {
        // Arrange
        String incomingEvent = "Invalid JSON";

        // Act
        portfolioCreationEventHandler.receive(incomingEvent);

        // Assert
        verify(fileService, times(0))
                .upload(anyString(), anyString());
    }
}
