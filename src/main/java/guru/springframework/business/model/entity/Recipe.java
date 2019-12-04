package guru.springframework.business.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String direction;
    private String url;
    private Integer servings;
    private Integer prepTime;
    private Integer cookTime;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set <Ingredient> ingredients;
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;
    @Lob
    private Byte[] image;

}
