package jek.repositories;

import jek.models.BasicIngredient;
import jek.services.system.DatabaseService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicIngredientRepository {
    private final DatabaseService databaseService;

    public BasicIngredientRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createBasicIngredient(BasicIngredient newBasicIngredient) {

        String query = "INSERT INTO basic_ingredients (basic_ingredient_name, amount_in_stock) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newBasicIngredient.getBasicIngredientName());
            ps.setInt(2, newBasicIngredient.getAmountInStock());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public int getBasicIngredientAmountInStockById(int basicIngredientId) {
        String query = "SELECT basic_ingredients.amount_in_stock FROM basic_ingredients WHERE basic_ingredient_id = ?;";
        int id = 0;
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, basicIngredientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                id = rs.getInt("amount_in_stock");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public boolean basicIngredientsIsEmpty(){
        String query = "SELECT * FROM basic_ingredients";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE

    public void updateBasicIngredientAmountInStockById(int basicIngredientId, int newAmountInStock) {
        String query = "UPDATE basic_ingredients SET amount_in_stock = ? WHERE basic_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newAmountInStock);
            ps.setInt(2, basicIngredientId);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE

    public void deleteAllBasicIngredients() {
        String query = "DELETE FROM basic_ingredients;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps = connection.prepareStatement("ALTER TABLE basic_ingredients AUTO_INCREMENT = 1;");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
