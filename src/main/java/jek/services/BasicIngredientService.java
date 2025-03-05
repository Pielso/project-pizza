package jek.services;

import jek.models.BasicIngredient;
import jek.repositories.BasicIngredientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicIngredientService {
    private final BasicIngredientRepository basicIngredientRepository;

    public BasicIngredientService(BasicIngredientRepository basicIngredientRepository) {
        this.basicIngredientRepository = basicIngredientRepository;
    }

    /**
     * <h3>Part of creating the empty inventory of BasicIngredients</h3>
     * <h5>Used when player registers or logs in</h5>
     * <p>There is counterparts for loading the amountInStock of RawIngredients and Toppings.</p>
     */
    public void createAllBasicIngredients() {
        if (basicIngredientRepository.basicIngredientsIsEmpty()){
            BasicIngredient dough = new BasicIngredient("Dough", 0);
            BasicIngredient tomatoSauce = new BasicIngredient("Tomato Sauce", 0);
            BasicIngredient cheese = new BasicIngredient("Cheese", 0);
            List <BasicIngredient> basicIngredients = new ArrayList<>(Arrays.asList(dough, tomatoSauce, cheese));

            for (BasicIngredient basicIngredient: basicIngredients){
                basicIngredientRepository.createBasicIngredient(basicIngredient);
            }
        }
    }

    public int getBasicIngredientAmountInStockById(int basicIngredientId){
        return basicIngredientRepository.getBasicIngredientAmountInStockById(basicIngredientId);
    }

    public boolean checkIfBasicIngredientsAmountInStockExists(){
        return getBasicIngredientAmountInStockById(1) > 0
                && getBasicIngredientAmountInStockById(2) > 0
                && getBasicIngredientAmountInStockById(3) > 0;
    }

    public void addDough(int amountToAdd) {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(1, getBasicIngredientAmountInStockById(1)+amountToAdd);
    }
    public void addTomatoSauce(int amountToAdd) {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(2, getBasicIngredientAmountInStockById(2)+amountToAdd);
    }
    public void addCheese(int amountToAdd){
        basicIngredientRepository.updateBasicIngredientAmountInStockById(3, getBasicIngredientAmountInStockById(3)+amountToAdd);
    }

    public void subtractDough(int amountToSubtract) {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(1, getBasicIngredientAmountInStockById(1)-amountToSubtract);
    }
    public void subtractTomatoSauce(int amountToSubtract) {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(2, getBasicIngredientAmountInStockById(2)-amountToSubtract);
    }
    public void subtractCheese(int amountToSubtract) {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(3, getBasicIngredientAmountInStockById(3)-amountToSubtract);
    }

    public void preparedOnePizza(){
        subtractDough(1);
        subtractTomatoSauce(1);
        subtractCheese(1);
    }

    /**
     * <h3>Part of loading amountInStock of BasicIngredients from DynamoDB</h3>
     * <h5>Used when already existing player logs in (see LoginController)</h5>
     * <p>There is counterparts for loading the amountInStock of RawIngredients and Toppings.</p>
     * @param amountInStock List of integer values retrieved from DynamoDB.
     */
    public void setAllAmountInStock(List <Integer> amountInStock) {
        int id = 1;
        for (Integer num : amountInStock) {
            basicIngredientRepository.updateBasicIngredientAmountInStockById(id, num);
            id++;
        }
    }

    /**
     * <h3>Part of purging all BasicIngredients between application shutdowns.</h3>
     * <h5>Resets the auto_increment in the sql-table</h5>
     * <p>There is counterparts for purging the amountInStock of RawIngredients and Toppings.</p>
     */
    public void deleteAllBasicIngredients() {
        basicIngredientRepository.deleteAllBasicIngredients();
    }
}
