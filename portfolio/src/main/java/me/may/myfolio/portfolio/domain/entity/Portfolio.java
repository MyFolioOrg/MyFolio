package me.may.myfolio.portfolio.domain.entity;

import jakarta.persistence.*;
import lombok.*;
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
    public long id;
    public String title;
    public Set<Category> categories;
    public long ownerId;

}
