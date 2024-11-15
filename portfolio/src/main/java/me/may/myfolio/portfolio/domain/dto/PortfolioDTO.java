package me.may.myfolio.portfolio.domain.dto;

import lombok.*;
import me.may.myfolio.portfolio.domain.Category;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDTO {
    public long id;
    public String title;
    public Set<Category> categories;
    public String content;
}
