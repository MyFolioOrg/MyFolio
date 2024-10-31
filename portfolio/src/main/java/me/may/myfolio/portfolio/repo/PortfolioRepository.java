package me.may.myfolio.portfolio.repo;

import me.may.myfolio.portfolio.domain.entity.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {}
