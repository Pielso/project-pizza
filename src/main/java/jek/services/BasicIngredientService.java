package jek.services;

import jek.models.BasicIngredient;
import jek.repositories.BasicIngredientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicIngredientService {
    BasicIngredientRepository basicIngredientRepository;

    public BasicIngredientService(BasicIngredientRepository basicIngredientRepository) {

    }

    public static List<BasicIngredient> createInventoryOfBasicIngredients(){
        BasicIngredient dough = new BasicIngredient("Dough", 0);
        BasicIngredient tomatoSauce = new BasicIngredient("Tomato Sauce", 0);
        BasicIngredient cheese = new BasicIngredient("Cheese", 0);
        return new ArrayList<>(Arrays.asList(dough, tomatoSauce, cheese));
    }
}
