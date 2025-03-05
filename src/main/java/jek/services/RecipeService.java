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

    public List<Recipe> getRecipesByUserId(int userId){
        return recipeRepository.getRecipesByUserId(userId);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public void deleteRecipe(Recipe recipe){

    }
}
