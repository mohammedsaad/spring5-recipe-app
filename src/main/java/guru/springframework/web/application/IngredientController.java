package guru.springframework.web.application;

import guru.springframework.business.model.entity.Ingredient;
import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.service.RecipeService;
import guru.springframework.business.service.UomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class IngredientController {
    RecipeService recipeServiceImpl;
    UomService uomServiceImpl;

    public IngredientController(RecipeService recipeServiceImpl, UomService uomServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
        this.uomServiceImpl = uomServiceImpl;
    }

    @GetMapping("/recipes/{recipeId}/ingredients")
    public String showIngredients(@PathVariable Long recipeId,  Model model){
      Recipe recipe = recipeServiceImpl.findById(recipeId);
      model.addAttribute("recipe",recipe);
       return "recipe/ingredients/list";
   }
    @GetMapping("/recipes/{recipeId}/ingredients/{ingredientId}/show")
    public String showIngredients(@PathVariable Long recipeId, @PathVariable Long ingredientId ,Model model){
        Recipe recipe = recipeServiceImpl.findById(recipeId);
        Optional result = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        model.addAttribute("ingredient",result.get());
        return "recipe/ingredients/show";
    }
    @GetMapping("/recipes/{recipeId}/ingredients/{ingredientId}/update")
    public String updateIngredientForm(@PathVariable Long recipeId, @PathVariable Long ingredientId ,Model model){
        Recipe recipe = recipeServiceImpl.findById(recipeId);
        Optional result = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        model.addAttribute("ingredient",result.get());
        model.addAttribute("uomList",uomServiceImpl.findAll());
        return "recipe/ingredients/update";
    }
    @GetMapping("/recipes/{recipeId}/ingredients/{ingredientId}/delete")
    String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
        Recipe recipe = recipeServiceImpl.findById(recipeId);
        Optional<Ingredient> result =recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        if(result.isPresent()){
            Ingredient ingredient= result.get();
            ingredient.setRecipe(null);
            recipe.getIngredients().remove(ingredient);
            recipeServiceImpl.save(recipe);
        }

        return "forward:/recipes/" + recipeId+"/ingredients/";
    }

    @PostMapping("/recipes/{recipeId}/ingredient")
    public String updateIngredient(@ModelAttribute("ingredient") Ingredient updatedIngredient,@PathVariable Long recipeId) {
       Recipe recipe = recipeServiceImpl.findById(recipeId);
       Optional<Ingredient> result =recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(updatedIngredient.getId())).findFirst();
        if(result.isPresent()){
           Ingredient ingredient= result.get();
           ingredient.setAmount(updatedIngredient.getAmount());
           ingredient.setDescription(updatedIngredient.getDescription());
           ingredient.setUom(uomServiceImpl.findById(updatedIngredient.getUom().getId()).get());
        }
       recipeServiceImpl.save(recipe);
        return "forward:/recipes/" + recipe.getId()+"/ingredients/"+updatedIngredient.getId()+"/show";
    }
}
