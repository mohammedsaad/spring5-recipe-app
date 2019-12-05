package guru.springframework.business.service;



import guru.springframework.business.model.entity.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}