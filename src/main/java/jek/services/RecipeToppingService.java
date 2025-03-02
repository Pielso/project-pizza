package jek.services;

import jek.models.RecipeTopping;
import jek.repositories.RecipeToppingRepository;

import java.util.List;

public class RecipeToppingService {
    private final RecipeToppingRepository recipeToppingRepository;

    public RecipeToppingService(RecipeToppingRepository recipeToppingRepository){
        this.recipeToppingRepository = recipeToppingRepository;
    }

    // CREATE
    public void createRecipeTopping(RecipeTopping recipeTopping){
        recipeToppingRepository.createRecipeTopping(recipeTopping);
    }

    // READ
    public List<RecipeTopping> getAllRecipeToppings(){
        return null;
    }

    public List<String> getAllRecipeToppingNamesByRecipeId(int recipeId){
        return recipeToppingRepository.getAllToppingNamesByRecipeId(recipeId);
    }

    public List<Integer> getAllToppingIdsByRecipeId(int recipeId){
        return recipeToppingRepository.getAllToppingIdByRecipeId(recipeId);
    }




}
