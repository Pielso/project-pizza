package jek.repositories;

import jek.models.Topping;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToppingRepository {
    private final DatabaseService databaseService;

    public ToppingRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createTopping(Topping newTopping) {

        String query = "INSERT INTO toppings (topping_name, amount_in_stock) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newTopping.getToppingName());
            ps.setInt(2, newTopping.getAmountInStock());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public Topping getToppingById(int toppingId){
        Topping topping = new Topping();
        String query = "SELECT topping_id, topping_name, amount_in_stock FROM toppings WHERE topping_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, toppingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                topping.setToppingId(rs.getInt("topping_id"));
                topping.setToppingName(rs.getString("topping_name"));
                topping.setAmountInStock(rs.getInt("amount_in_stock"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topping;
    }

    public int getToppingAmountInStockById(int toppingId){
        String query = "SELECT amount_in_stock FROM toppings WHERE topping_id = ?";
        int amount = 0;
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, toppingId);
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

    public void updateToppingAmountInStockById(int toppingId, int amountInStock){
        String query = "UPDATE toppings SET amount_in_stock = ? WHERE topping_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, amountInStock);
            ps.setInt(2, toppingId);
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE



}
