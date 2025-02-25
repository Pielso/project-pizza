package jek.services;

import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IngredientInventoryService {
    DatabaseService databaseService;

    public IngredientInventoryService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Map <String, Integer> getAllAmountInStock(){
        String query = "SELECT raw_ingredients.raw_ingredient_name, raw_ingredients.amount_in_stock, basic_ingredients.basic_ingredient_name, basic_ingredients.amount_in_stock, toppings.topping_name, toppings.amount_in_stock FROM raw_ingredients JOIN basic_ingredients JOIN toppings";
        Map <String, Integer> ingredientsInventory = new HashMap<>();
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ingredientsInventory.put(rs.getString("raw_ingredient_name"), rs.getInt("amount_in_stock"));
                ingredientsInventory.put(rs.getString("basic_ingredient_name"), rs.getInt("amount_in_stock"));
                ingredientsInventory.put(rs.getString("topping_name"), rs.getInt("amount_in_stock"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredientsInventory;

    }
}
