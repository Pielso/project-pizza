package jek.models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map <String, Integer> rawIngredients;
    private Map <String, Integer> basicIngredients;
    private Map <String, Integer> toppings;

    public Inventory(int userId) {
        this.rawIngredients = new HashMap<>();
        this.basicIngredients = new HashMap<>();
        this.toppings = new HashMap<>();
    }
}
