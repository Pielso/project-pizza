package jek.services;

import jek.models.Recipe;
import jek.repositories.RecipeRepository;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void createRecipe(Recipe recipe){
        recipeRepository.createRecipe(recipe);
    }

    public Recipe getRecipeByName(String name){
        return recipeRepository.getRecipeByName(name);
    }
}
