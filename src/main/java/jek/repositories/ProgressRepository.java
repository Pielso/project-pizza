package jek.repositories;

import jek.models.Customer;
import jek.models.Progress;
import jek.services.system.DatabaseService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressRepository {
    private DatabaseService databaseService;

    public ProgressRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void SaveNewProgress(int userId, BigDecimal cash, BigDecimal loan, int interestRate, int customersPerDay, int restaurantSize, int daysPlayed) {

        String query = "INSERT INTO progress (user_id, cash, loan, interest_rate, customers_per_day, restaurant_size, days_played) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setBigDecimal(2, cash);
            ps.setBigDecimal(3, loan);
            ps.setInt(4, interestRate);
            ps.setInt(5, customersPerDay);
            ps.setInt(6, restaurantSize);
            ps.setInt(7, daysPlayed);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // READ

    public Progress getProgressById(int userId){
        Progress progress = new Progress();
        String query = "SELECT user_id, cash, loan, interest_rate, customers_per_day, restaurant_size, days_played FROM progress WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                progress.setUserId(rs.getInt("user_id"));
                progress.setCash(rs.getBigDecimal("cash"));
                progress.setLoan(rs.getBigDecimal("loan"));
                progress.setInterestRate(rs.getInt("interest_rate"));
                progress.setCustomersPerDay(rs.getInt("customers_per_day"));
                progress.setRestaurantSize(rs.getInt("restaurant_size"));
                progress.setDaysPlayed(rs.getInt("days_played"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return progress;
    }

    // UPDATE

    public void UpdateProgressById(int userId, BigDecimal cash, BigDecimal loan, int interestRate, int customersPerDay, int restaurantSize, int daysPlayed){
        String query = "UPDATE progress SET cash = ?, loan = ?, interest_rate = ?, customers_per_day = ?, restaurant_size = ?, days_played = ? WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBigDecimal(1, cash);
            ps.setBigDecimal(2, loan);
            ps.setInt(3, interestRate);
            ps.setInt(4, customersPerDay);
            ps.setInt(5, restaurantSize);
            ps.setInt(6, daysPlayed);
            ps.setInt(7, userId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE



}
