package jek.services;

import jek.models.Recipe;
import jek.repositories.RecipeRepository;

import java.util.List;

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

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public List<Recipe> getRecipesByUserId(int userId){
        return recipeRepository.getRecipesByUserId(userId);
    }

    public void updateRecipe(Recipe recipe){
        recipeRepository.updateRecipeById(recipe.getRecipeId(), recipe.getRecipeName(), recipe.getUserId());
    }

    public void deleteRecipe(Recipe recipe){

    }
}
