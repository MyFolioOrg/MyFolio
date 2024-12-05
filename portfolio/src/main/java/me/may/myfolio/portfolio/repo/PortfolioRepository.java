package me.may.myfolio.portfolio.repo;

import me.may.myfolio.portfolio.domain.entity.Portfolio;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!load-test")
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
}
