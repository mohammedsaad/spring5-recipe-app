package guru.springframework.web.application;

import guru.springframework.business.model.entity.Recipe;
import guru.springframework.business.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerUnitTest {
    @MockBean
    RecipeService recipeServiceImpl;
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateRecipe() throws Exception {
        //given
        Recipe recipe= new Recipe();
        recipe.setId(2L);
        //when
        when(recipeServiceImpl.save(any(Recipe.class))).thenReturn(recipe);

        //then
        mockMvc.perform(post("/recipes").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id","")).andExpect(view().name(RecipeController.RECIPE_UPDATE_PAGE)).andExpect(model().attributeExists("recipe"));
      verify(recipeServiceImpl,never()).save(any());
    }
}
