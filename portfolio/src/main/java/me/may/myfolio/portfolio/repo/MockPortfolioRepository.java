package me.may.myfolio.portfolio.repo;

import me.may.myfolio.portfolio.domain.entity.Portfolio;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("load-test")
public class MockPortfolioRepository implements PortfolioRepository {
    @Override
    public <S extends Portfolio> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends Portfolio> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Portfolio> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Portfolio> findAll() {
        return null;
    }

    @Override
    public Iterable<Portfolio> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Portfolio entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Portfolio> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
