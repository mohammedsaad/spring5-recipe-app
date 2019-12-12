package guru.springframework.web.application;

import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.service.ImageService;
import guru.springframework.business.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipes/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipes/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(id, file);

        return "redirect:/recipes/" + id ;
    }
    @GetMapping("recipes/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeService.findById(id);

        if (recipe.getImage() != null) {
            byte[] byteArray = new byte[recipe.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipe.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
