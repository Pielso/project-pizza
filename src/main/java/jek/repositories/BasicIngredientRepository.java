package jek.repositories;

import jek.models.BasicIngredient;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicIngredientRepository {
    private DatabaseService databaseService;

    public BasicIngredientRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void SaveBasicIngredient(BasicIngredient newBasicIngredient) {

        String query = "INSERT INTO basic_ingredients (basic_ingredient_name, amount_in_stock) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
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

    public BasicIngredient getBasicIngredientById(int basicIngredientId){
        BasicIngredient basicIngredient = new BasicIngredient();
        String query = "SELECT basic_ingredient_id, basic_ingredient_name, amount_in_stock FROM basic_ingredients WHERE basic_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, basicIngredientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                basicIngredient.setBasicIngredientId(rs.getInt("basic_ingredient_id"));
                basicIngredient.setBasicIngredientName(rs.getString("basic_ingredient_name"));
                basicIngredient.setAmountInStock(rs.getInt("amount_in_stock"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return basicIngredient;
    }

    // UPDATE

    public void UpdateBasicIngredientById(int basicIngredientId, String basicIngredientName, int amountInStock){
        String query = "UPDATE basic_ingredients SET basic_ingredient_name = ?, amount_in_stock = ? WHERE basic_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, basicIngredientId);
            ps.setString(2, basicIngredientName);
            ps.setInt(3, amountInStock);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE



}
