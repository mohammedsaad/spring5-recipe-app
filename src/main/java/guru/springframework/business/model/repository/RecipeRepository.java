package guru.springframework.business.model.repository;

import guru.springframework.business.model.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
