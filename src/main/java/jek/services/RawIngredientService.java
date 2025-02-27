package jek.services;

import jek.models.RawIngredient;
import jek.repositories.RawIngredientRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RawIngredientService {
    private final RawIngredientRepository rawIngredientRepository;

    public RawIngredientService(RawIngredientRepository rawIngredientRepository) {
        this.rawIngredientRepository = rawIngredientRepository;
    }

    // Create
    public void createAllRawIngredients() {
        for (RawIngredient rawIngredient: allRawIngredients()){
            rawIngredientRepository.createRawIngredient(rawIngredient);
        }
    }

    // Read
    public int getRawIngredientAmountInStockById(int rawIngredientId){
        return rawIngredientRepository.getRawIngredientAmountInStockById(rawIngredientId);
    }

    // Update
    public void addRawIngredientAmountInStock(int rawIngredientId, int amountToAdd){
        int before = rawIngredientRepository.getRawIngredientAmountInStockById(rawIngredientId);
        rawIngredientRepository.updateRawIngredientAmountInStockById(rawIngredientId, before + amountToAdd);
    }

    public List<RawIngredient> allRawIngredients(){
        RawIngredient flour = new RawIngredient("Flour", 0);
        RawIngredient yeast = new RawIngredient("Yeast", 0);
        RawIngredient oliveOil = new RawIngredient("Olive Oil", 0);
        RawIngredient tomatoes = new RawIngredient("Tomatoes", 0);
        RawIngredient basil = new RawIngredient("Basil", 0);
        RawIngredient garlic = new RawIngredient("Garlic", 0);
        return new ArrayList<>(Arrays.asList(flour, yeast, oliveOil, tomatoes, basil, garlic));
    }

    public boolean hasEnoughIngredientsForDough() {
        return (getRawIngredientAmountInStockById(1) > 9 && getRawIngredientAmountInStockById(2) > 9 && getRawIngredientAmountInStockById(3) > 9);
    }

    public void subtractIngredientsForDough() {
        rawIngredientRepository.updateRawIngredientAmountInStockById(1, getRawIngredientAmountInStockById(1)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(2, getRawIngredientAmountInStockById(2)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(3, getRawIngredientAmountInStockById(3)-10);
    }

    public boolean hasEnoughIngredientsTomatoSauce() {
        return (getRawIngredientAmountInStockById(4) > 9 && getRawIngredientAmountInStockById(5) > 9 && getRawIngredientAmountInStockById(6) > 9);
    }

    public void subtractIngredientsForTomatoSauce() {
        rawIngredientRepository.updateRawIngredientAmountInStockById(4, getRawIngredientAmountInStockById(4)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(5, getRawIngredientAmountInStockById(5)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(6, getRawIngredientAmountInStockById(6)-10);
    }
}

