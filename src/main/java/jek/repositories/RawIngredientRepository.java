package jek.repositories;

import jek.models.RawIngredient;
import jek.services.system.DatabaseService;

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

    public void SaveRawIngredient(RawIngredient newRawIngredient) {
        String query = "INSERT INTO raw_ingredients (raw_ingredient_name, amount_in_stock) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newRawIngredient.getRawIngredientName());
            ps.setInt(2, newRawIngredient.getAmountInStock());

            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return rawIngredient;
    }

    public int getRawIngredientAmountInStockById(int rawIngredientId){
        String query = "SELECT amount_in_stock FROM pizza.raw_ingredients WHERE raw_ingredient_id = ?";
        int amount = 0;
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rawIngredientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                amount = rs.getInt("amount_in_stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }


    // UPDATE

    public void updateRawIngredientAmountInStockById(int rawIngredientId, int amountInStock){
        String query = "UPDATE raw_ingredients SET raw_ingredients.amount_in_stock = ? WHERE raw_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, amountInStock);
            ps.setInt(2, rawIngredientId);
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE



}
