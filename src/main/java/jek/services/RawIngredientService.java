package jek.services;

import jek.models.RawIngredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RawIngredientService {
    public static List<RawIngredient> createInventoryOfRawIngredients(){
        RawIngredient flour = new RawIngredient("Flour", 0);
        RawIngredient yeast = new RawIngredient("Yeast", 0);
        RawIngredient oliveOil = new RawIngredient("Olive Oil", 0);
        RawIngredient tomatoes = new RawIngredient("Tomatoes", 0);
        RawIngredient basil = new RawIngredient("Basil", 0);
        RawIngredient garlic = new RawIngredient("Garlic", 0);
        return new ArrayList<>(Arrays.asList(flour, yeast, oliveOil, tomatoes, basil, garlic));
    }
}
