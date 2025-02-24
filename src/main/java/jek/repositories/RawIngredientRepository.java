package jek.repositories;

import jek.models.Progress;
import jek.models.RawIngredient;
import jek.services.system.DatabaseService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RawIngredientRepository {
    private DatabaseService databaseService;

    public RawIngredientRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void SaveNewRawIngredient(RawIngredient newRawIngredient) {

        String query = "INSERT INTO raw_ingredients (raw_ingredient_name, amount_in_stock) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newRawIngredient.getRawIngredientName());
            ps.setInt(2, newRawIngredient.getAmountInStock());

            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // READ

    public RawIngredient getRawIngredientById(int rawIngredientId){
        RawIngredient rawIngredient = new RawIngredient();
        String query = "SELECT raw_ingredient_id, raw_ingredient_name, amount_in_stock FROM raw_ingredients WHERE raw_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rawIngredientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                rawIngredient.setRawIngredientId(rs.getInt("raw_ingredient_id"));
                rawIngredient.setRawIngredientName(rs.getString("raw_ingredient_name"));
                rawIngredient.setAmountInStock(rs.getInt("amount_in_stock"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rawIngredient;
    }


    // UPDATE

    public void UpdateRawIngredientById(int rawIngredientId, String rawIngredientName, int amountInStock){
        String query = "UPDATE raw_ingredients SET raw_ingredient_name = ?, amount_in_stock = ? WHERE raw_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, rawIngredientName);
            ps.setInt(2, amountInStock);
            ps.setInt(3, rawIngredientId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE



}
