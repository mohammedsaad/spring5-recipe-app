package guru.springframework.business.model.entity;


import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Transactional
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    @Lob
    private String direction;
    private String url;
    private Integer servings;
    private Integer prepTime;
    private Integer cookTime;
    @Lob
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
