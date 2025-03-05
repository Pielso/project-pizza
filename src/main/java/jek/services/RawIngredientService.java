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

    /**
     * <h3>Part of creating the empty inventory of RawIngredients</h3>
     * <h5>Used when player registers or logs in</h5>
     * <p>There is counterparts for loading the amountInStock of BasicIngredients and Toppings.</p>
     */
    public void createAllRawIngredients() {
        if (rawIngredientRepository.rawIngredientsIsEmpty()){
            RawIngredient flour = new RawIngredient("Flour", 0);
            RawIngredient yeast = new RawIngredient("Yeast", 0);
            RawIngredient oliveOil = new RawIngredient("Olive Oil", 0);
            RawIngredient tomatoes = new RawIngredient("Tomatoes", 0);
            RawIngredient basil = new RawIngredient("Basil", 0);
            RawIngredient garlic = new RawIngredient("Garlic", 0);
            List <RawIngredient> rawIngredients = new ArrayList<>(Arrays.asList(flour, yeast, oliveOil, tomatoes, basil, garlic));

            for (RawIngredient rawIngredient: rawIngredients){
                rawIngredientRepository.createRawIngredient(rawIngredient);
            }
        }
    }

    public int getRawIngredientAmountInStockById(int rawIngredientId){
        return rawIngredientRepository.getRawIngredientAmountInStockById(rawIngredientId);
    }

    public boolean hasEnoughIngredientsForDough() {
        return (getRawIngredientAmountInStockById(1) > 9 && getRawIngredientAmountInStockById(2) > 9 && getRawIngredientAmountInStockById(3) > 9);
    }

    public boolean hasEnoughIngredientsTomatoSauce() {
        return (getRawIngredientAmountInStockById(4) > 9 && getRawIngredientAmountInStockById(5) > 9 && getRawIngredientAmountInStockById(6) > 9);
    }

    public void addRawIngredientAmountInStock(int rawIngredientId, int amountToAdd){
        int before = rawIngredientRepository.getRawIngredientAmountInStockById(rawIngredientId);
        rawIngredientRepository.updateRawIngredientAmountInStockById(rawIngredientId, before + amountToAdd);
    }

    public void subtractIngredientsForDough() {
        rawIngredientRepository.updateRawIngredientAmountInStockById(1, getRawIngredientAmountInStockById(1)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(2, getRawIngredientAmountInStockById(2)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(3, getRawIngredientAmountInStockById(3)-10);
    }

    public void subtractIngredientsForTomatoSauce() {
        rawIngredientRepository.updateRawIngredientAmountInStockById(4, getRawIngredientAmountInStockById(4)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(5, getRawIngredientAmountInStockById(5)-10);
        rawIngredientRepository.updateRawIngredientAmountInStockById(6, getRawIngredientAmountInStockById(6)-10);
    }

    /**
     * <h3>Part of loading amountInStock of RawIngredients from DynamoDB</h3>
     * <h5>Used when already existing player logs in (see LoginController)</h5>
     * <p>There is counterparts for loading the amountInStock of BasicIngredients and Toppings.</p>
     * @param amountInStock List of integer values retrieved from DynamoDB.
     */
    public void setAllAmountInStock(List <Integer> amountInStock) {
        int id = 1;
        for (Integer num : amountInStock) {
            rawIngredientRepository.updateRawIngredientAmountInStockById(id, num);
            id++;
        }
    }

    /**
     * <h3>Part of purging all RawIngredients between application shutdowns.</h3>
     * <h5>Resets the auto_increment in the sql-table</h5>
     * <p>There is counterparts for purging the amountInStock of BasicIngredients and Toppings.</p>
     */
    public void deleteAllRawIngredients() {
        rawIngredientRepository.deleteAllRawIngredients();
    }

}

