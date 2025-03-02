package jek.repositories;

import jek.models.Recipe;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private final DatabaseService databaseService;

    public RecipeRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createRecipe(Recipe newRecipe) {

        String query = "INSERT INTO recipes (recipe_name, user_id) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newRecipe.getRecipeName());
            ps.setInt(2, newRecipe.getUserId());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public Recipe getRecipeById(int recipeId){
        Recipe recipe = new Recipe();
        String query = "SELECT recipe_id, recipe_name, user_id FROM recipes WHERE recipe_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recipeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                recipe.setRecipeId(rs.getInt("recipe_id"));
                recipe.setRecipeName(rs.getString("recipe_name"));
                recipe.setUserId(rs.getInt("user_id"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }

    public Recipe getRecipeByName(String name){
        Recipe recipe = new Recipe();
        String query = "SELECT recipe_id, recipe_name, user_id FROM recipes WHERE recipe_name = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                recipe.setRecipeId(rs.getInt("recipe_id"));
                recipe.setRecipeName(rs.getString("recipe_name"));
                recipe.setUserId(rs.getInt("user_id"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }

    public List<Recipe> getAllRecipes(){
        String query = "SELECT * FROM recipes;";
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Recipe recipe = new Recipe();
                recipe.setRecipeId(rs.getInt("recipe_id"));
                recipe.setRecipeName(rs.getString("recipe_name"));
                recipe.setUserId(rs.getInt("user_id"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    // UPDATE

    public void updateRecipeById(int recipeId, String recipeName, int userId){
        String query = "UPDATE recipes SET recipe_name = ?, user_id = ? WHERE recipe_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, recipeName);
            ps.setInt(2, userId);
            ps.setInt(3,recipeId);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE

    public void deleteRecipeById(int recipeId){
        String query = "DELETE FROM recipes WHERE recipe_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recipeId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
