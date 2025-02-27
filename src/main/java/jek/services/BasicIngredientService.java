package jek.services;

import jek.models.BasicIngredient;
import jek.repositories.BasicIngredientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicIngredientService {
    private final BasicIngredientRepository basicIngredientRepository;

    public BasicIngredientService(BasicIngredientRepository basicIngredientRepository) {
        this.basicIngredientRepository = basicIngredientRepository;
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

    public void addDough() {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(1, getBasicIngredientAmountInStockById(1)+10);
    }

    public void addTomatoSauce() {
        basicIngredientRepository.updateBasicIngredientAmountInStockById(2, getBasicIngredientAmountInStockById(2)+10);
    }

    public void addCheese(int amountToAdd){
        basicIngredientRepository.updateBasicIngredientAmountInStockById(3, getBasicIngredientAmountInStockById(3)+amountToAdd);
    }
}
