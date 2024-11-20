package me.may.myfolio.portfolio.service.impl;

import me.may.myfolio.portfolio.messaging.event.EventPublisherImpl;
import me.may.myfolio.common.messaging.event.PortfolioCreationEvent;
import me.may.myfolio.portfolio.domain.entity.Portfolio;
import me.may.myfolio.portfolio.repo.PortfolioRepository;
import me.may.myfolio.portfolio.service.PortfolioService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository repo;
    private final EventPublisherImpl<PortfolioCreationEvent> publisher;
    public PortfolioServiceImpl(PortfolioRepository repo, EventPublisherImpl<PortfolioCreationEvent> publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    @Override
    public Portfolio create(Portfolio portfolio, String content) {
        Portfolio saved = repo.save(portfolio);
        try {
            publisher.publish(new PortfolioCreationEvent(saved.getTitle(), saved.getId(), saved.getOwnerId(), content));
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }
}
