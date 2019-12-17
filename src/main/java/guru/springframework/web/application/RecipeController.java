package guru.springframework.web.application;

import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller

public class RecipeController {
    public static final String RECIPE_UPDATE_PAGE = "recipe/update";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeServiceImpl) {
        this.recipeService = recipeServiceImpl;
    }

    @GetMapping("/recipes/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/recipes/{id}/update")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return RECIPE_UPDATE_PAGE;
    }

    @GetMapping("/recipes/{id}/delete")
    String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/recipes/")
    public String updateRecipe(@Valid @ModelAttribute("recipe") Recipe updatedRecipe, BindingResult validationResult) {

        if (validationResult.hasErrors()) return RECIPE_UPDATE_PAGE;
        recipeService.save(updatedRecipe);
        return "redirect:/recipes/" + updatedRecipe.getId();
    }
}
