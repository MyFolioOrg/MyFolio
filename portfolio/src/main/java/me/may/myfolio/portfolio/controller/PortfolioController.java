package me.may.myfolio.portfolio.controller;

import me.may.myfolio.common.mapper.Mapper;
import me.may.myfolio.portfolio.domain.Category;
import me.may.myfolio.portfolio.domain.dto.PortfolioDTO;
import me.may.myfolio.portfolio.domain.entity.Portfolio;
import me.may.myfolio.portfolio.mapper.PortfolioMapper;
import me.may.myfolio.portfolio.repo.PortfolioRepository;
import me.may.myfolio.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RestController
@RequestMapping("api/portfolio")
public class PortfolioController {

    private final PortfolioService service;
    private final PortfolioMapper mapper;

    public PortfolioController(PortfolioService service, PortfolioMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong!");
    }

    @PostMapping()
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody PortfolioDTO portfolioDto) {
        Portfolio portfolio = mapper.mapFrom(portfolioDto);
        portfolio.setOwnerId(1L); // <- TODO: get from jwt
        Portfolio created = service.create(portfolio);
        return ResponseEntity.ok(created);
    }
}
