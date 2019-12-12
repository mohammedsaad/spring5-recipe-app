package guru.springframework.web.application;

import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeServiceImpl) {
        this.recipeService = recipeServiceImpl;
    }

    @GetMapping("/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/update";
    }

    @GetMapping("/{id}/delete")
    String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping()
    public String updateRecipe(@ModelAttribute Recipe updatedRecipe) {
        recipeService.save(updatedRecipe);
        return "redirect:/recipes/" + updatedRecipe.getId();
    }
}
