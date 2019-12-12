package guru.springframework.business.service;

import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.model.repository.RecipeRepository;
import guru.springframework.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
    @Transactional
   public  Recipe findById(Long id){
       Optional<Recipe> recipeOptional =recipeRepository.findById(id);
       if (!recipeOptional.isPresent()) {
           throw new ResourceNotFoundException("Couldn't find recipe with id "+id);
       }

       return recipeOptional.get();

   }

    @Override
    @Transactional
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}