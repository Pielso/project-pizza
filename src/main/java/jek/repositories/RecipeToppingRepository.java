package jek.repositories;

import jek.models.RecipeTopping;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeToppingRepository {
    private final DatabaseService databaseService;

    public RecipeToppingRepository(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    // CREATE

    public void createRecipeTopping(RecipeTopping recipeTopping){
        String query = "INSERT INTO recipe_topping (recipe_id, topping_id) VALUES (?, ?)";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recipeTopping.getRecipeId());
            ps.setInt(2, recipeTopping.getToppingId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public List<String> getAllToppingNamesByRecipeId(int recipeId){
        String query = "SELECT toppings.topping_name FROM toppings JOIN recipe_topping on toppings.topping_id = recipe_topping.topping_id WHERE recipe_id = ?";
        List<String> toppingsInRecipe = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recipeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                toppingsInRecipe.add((rs.getString("topping_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toppingsInRecipe;
    }

    public List<Integer> getAllToppingIdByRecipeId(int recipeId){
        String query = "SELECT toppings.topping_id FROM toppings JOIN recipe_topping on toppings.topping_id = recipe_topping.topping_id WHERE recipe_id = ?";
        List<Integer> toppingIdsInRecipe = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recipeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                toppingIdsInRecipe.add((rs.getInt("topping_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toppingIdsInRecipe;
    }

}
