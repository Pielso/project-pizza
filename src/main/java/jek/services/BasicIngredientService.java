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

    // Basic CRUD-Referrals

    public void createBasicIngredient(BasicIngredient basicIngredient) {
        basicIngredientRepository.createBasicIngredient(basicIngredient);
    }
    public BasicIngredient getBasicIngredient(int id) {
        return basicIngredientRepository.getBasicIngredientById(id);
    }






    public void createAllBasicIngredients() {

        for (BasicIngredient basicIngredient: allBasicIngredients()){
            basicIngredientRepository.createBasicIngredient(basicIngredient);
        }
    }



    public int getBasicIngredientAmountInStockById(int basicIngredientId){
        return basicIngredientRepository.getBasicIngredientAmountInStockById(basicIngredientId);
    }

    public List<BasicIngredient> allBasicIngredients(){
        BasicIngredient dough = new BasicIngredient("Dough", 0);
        BasicIngredient tomatoSauce = new BasicIngredient("Tomato Sauce", 0);
        BasicIngredient cheese = new BasicIngredient("Cheese", 0);
        return new ArrayList<>(Arrays.asList(dough, tomatoSauce, cheese));
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


    public boolean checkIfBasicIngredientsAmountInStockExists(){
        return getBasicIngredientAmountInStockById(1) > 0
                && getBasicIngredientAmountInStockById(2) > 0
                && getBasicIngredientAmountInStockById(3) > 0;
    }
}
