package jek.services;

import jek.models.RecipeTopping;
import jek.repositories.RecipeToppingRepository;

public class RecipeToppingService {
    RecipeToppingRepository recipeToppingRepository;

    public RecipeToppingService(RecipeToppingRepository recipeToppingRepository){
        this.recipeToppingRepository = recipeToppingRepository;
    }

    public void createRecipeTopping(RecipeTopping recipeTopping){
        recipeToppingRepository.createRecipeTopping(recipeTopping);
    }
}
