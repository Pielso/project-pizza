package jek.repositories;

import jek.models.RawIngredient;
import jek.services.system.DatabaseService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RawIngredientRepository {
    private final DatabaseService databaseService;

    public RawIngredientRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createRawIngredient(RawIngredient newRawIngredient) {
        String query = "INSERT INTO raw_ingredients (raw_ingredient_name, amount_in_stock) VALUES (?, ?);";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newRawIngredient.getRawIngredientName());
            ps.setInt(2, newRawIngredient.getAmountInStock());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ

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

    public boolean rawIngredientsIsEmpty(){
        String query = "SELECT * FROM raw_ingredients";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE

    public void updateRawIngredientAmountInStockById(int rawIngredientId, int amountInStock){
        String query = "UPDATE raw_ingredients SET raw_ingredients.amount_in_stock = ? WHERE raw_ingredient_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, amountInStock);
            ps.setInt(2, rawIngredientId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE

    /**
     * <h3>Part of purging all RawIngredients between application shutdowns</h3>
     * <h5>Needs to reset the auto_increment so that the next player have RawIngredients on same raw_ingredient_id</h5>
     */
    public void deleteAllRawIngredients() {
        String query = "DELETE FROM raw_ingredients;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps = connection.prepareStatement("ALTER TABLE raw_ingredients AUTO_INCREMENT = 1;");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
