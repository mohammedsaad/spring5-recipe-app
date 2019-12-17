package guru.springframework.business.model.entity;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Transactional
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String source;
    @Lob
    @NotBlank
    private String direction;
    @URL
    private String url;
    @Min(1)
    @Max(999)
    private Integer servings;
    @Min(1)
    @Max(999)
    private Integer prepTime;
    @Min(1)
    @Max(999)
    private Integer cookTime;
    @Lob
    @NotBlank
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set <Ingredient> ingredients= new HashSet <>();
    ;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Note note;
    @Lob
    private Byte[] image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories= new HashSet <>();

    public void setNote(Note note) {
        if (note != null) {
            this.note = note;
            note.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
