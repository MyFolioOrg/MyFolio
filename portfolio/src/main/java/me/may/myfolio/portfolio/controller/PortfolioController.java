package me.may.myfolio.portfolio.controller;

import me.may.myfolio.portfolio.domain.Category;
import me.may.myfolio.portfolio.domain.entity.Portfolio;
import me.may.myfolio.portfolio.repo.PortfolioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Controller
@RestController
@RequestMapping("api/portfolio")
public class PortfolioController {

    private final PortfolioRepository repo;

    public PortfolioController(PortfolioRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong!");
    }

    @PostMapping("/createTest")
    public ResponseEntity<Portfolio> createTest() {
        Portfolio portfolio = new Portfolio();
        portfolio.setOwnerId(1);
        portfolio.setTitle("Test Portfolio");
        Set<Category> categories = new HashSet<>();
        categories.add(Category.SOFTWARE);
        categories.add(Category.GAME_DEVELOPMENT);
        portfolio.setCategories(categories);
        Portfolio created = repo.save(portfolio);
        return ResponseEntity.ok(created);
    }
}
