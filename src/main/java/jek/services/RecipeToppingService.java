package jek.services;

import jek.models.RecipeTopping;
import jek.repositories.RecipeToppingRepository;

import java.util.List;

public class RecipeToppingService {
    private final RecipeToppingRepository recipeToppingRepository;

    public RecipeToppingService(RecipeToppingRepository recipeToppingRepository){
        this.recipeToppingRepository = recipeToppingRepository;
    }

    public void createRecipeTopping(RecipeTopping recipeTopping){
        recipeToppingRepository.createRecipeTopping(recipeTopping);
    }

    public List<String> getAllRecipeToppingNamesByRecipeId(int recipeId){
        return recipeToppingRepository.getAllToppingNamesByRecipeId(recipeId);
    }

    public List<Integer> getAllToppingIdByRecipeId(int recipeId){
        return recipeToppingRepository.getAllToppingIdByRecipeId(recipeId);
    }




}
