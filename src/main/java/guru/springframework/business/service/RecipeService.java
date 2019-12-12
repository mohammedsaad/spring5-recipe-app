package guru.springframework.business.service;


import guru.springframework.business.model.entity.Recipe;

import java.util.Set;

public interface RecipeService {
    Recipe save(Recipe recipe);
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    void deleteById(Long l);
}