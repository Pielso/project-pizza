package jek.services;

import jek.models.RawIngredient;
import jek.repositories.RawIngredientRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RawIngredientService {
    private final RawIngredientRepository rawIngredientRepository;

    public RawIngredientService(RawIngredientRepository rawIngredientRepository) {
        this.rawIngredientRepository = rawIngredientRepository;
    }

    public void createAllRawIngredients() throws SQLException {
        for (RawIngredient rawIngredient: allRawIngredients()){
            rawIngredientRepository.SaveRawIngredient(rawIngredient);
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
}
