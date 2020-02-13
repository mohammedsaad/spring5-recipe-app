package guru.springframework.web.application;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)*/
public class RecipeControllerUnitTest {
  /*  @MockBean
    RecipeService recipeServiceImpl;
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
*/
   /* @Test
    public void updateRecipe() throws Exception {
        //given
        Recipe recipe= new Recipe();
        recipe.setId(2L);
        //when
        when(recipeServiceImpl.save(any(Recipe.class))).thenReturn(recipe);

        //then
        mockMvc.perform(post("/recipes").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id","")).andExpect(view().name(RecipeController.RECIPE_UPDATE_PAGE)).andExpect(model().attributeExists("recipe"));
      verify(recipeServiceImpl,never()).save(any());
    }*/
}
