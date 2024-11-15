package me.may.myfolio.portfolio.repo;

import me.may.myfolio.portfolio.domain.entity.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {}
