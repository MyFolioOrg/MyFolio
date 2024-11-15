package me.may.myfolio.portfolio.service.impl;

import me.may.myfolio.portfolio.domain.entity.Portfolio;
import me.may.myfolio.portfolio.repo.PortfolioRepository;
import me.may.myfolio.portfolio.service.PortfolioService;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository repo;
    public PortfolioServiceImpl(PortfolioRepository repo) {
        this.repo = repo;
    }

    @Override
    public Portfolio create(Portfolio portfolio) {
        return repo.save(portfolio);
    }
}
