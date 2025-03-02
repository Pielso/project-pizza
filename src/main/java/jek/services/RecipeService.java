package jek.services;

import jek.models.Recipe;
import jek.repositories.RecipeRepository;

import java.util.List;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // CREATE
    public void createRecipe(Recipe recipe){
        recipeRepository.createRecipe(recipe);
    }

    // READ
    public Recipe getRecipeByName(String name){
        return recipeRepository.getRecipeByName(name);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    // UPDATE
    public void updateRecipe(Recipe recipe){
        recipeRepository.updateRecipeById(recipe.getRecipeId(), recipe.getRecipeName(), recipe.getUserId());
    }

    // DELETE
    public void deleteRecipe(Recipe recipe){

    }


}
