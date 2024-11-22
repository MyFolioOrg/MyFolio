package me.may.myfolio.portfolio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.may.myfolio.portfolio.domain.Category;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_id_seq")
    private Long id;
    private String title;
    private Set<Category> categories;
    private Long ownerId;
}
