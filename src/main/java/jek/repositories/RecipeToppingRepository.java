package jek.repositories;

import jek.models.RecipeTopping;
import jek.services.RecipeService;
import jek.services.RecipeToppingService;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipeToppingRepository {
    DatabaseService databaseService;

    public RecipeToppingRepository(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

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

}
