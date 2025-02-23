package jek.services;

import jek.controllers.LoginController;
import jek.models.Progress;
import jek.services.system.DatabaseService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressService {

    public static void saveNewProgress(int userId, BigDecimal cash, BigDecimal loan, int interestRate, int customersPerDay, int restaurantSize, int daysPlayed){
        try (Connection connection = DatabaseService.getConnection()){
            String query = "INSERT INTO progress (user_id, cash, loan, interest_rate, customers_per_day, restaurant_size, days_played) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setBigDecimal(2, cash);
            ps.setBigDecimal(3, loan);
            ps.setInt(4, interestRate);
            ps.setInt(5, customersPerDay);
            ps.setInt(6, restaurantSize);
            ps.setInt(7, daysPlayed);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Progress loadProgressFromDatabase(int userId){
        String query = "SELECT * FROM progress WHERE user_id = ?;";
        Progress progress = null;
        try (Connection connection = DatabaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                progress = new Progress(
                        rs.getInt("user_id"),
                        rs.getBigDecimal("cash"),
                        rs.getBigDecimal("loan"),
                        rs.getInt("interest_rate"),
                        rs.getInt("customers_per_day"),
                        rs.getInt("restaurant_size"),
                        rs.getInt("days_played"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return progress;
    }

    public static Progress getProgress(){
        return LoginController.activeProgress;
    }
}
