package jek.repositories;

import jek.models.Progress;
import jek.services.system.DatabaseService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressRepository {
    private final DatabaseService databaseService;

    public ProgressRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createProgress(Progress newProgress) {
        String query = "INSERT INTO progress (user_id, cash, loan, interest_rate, customers_per_day, restaurant_size, days_played) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newProgress.getUserId());
            ps.setBigDecimal(2, newProgress.getCash());
            ps.setBigDecimal(3, newProgress.getLoan());
            ps.setInt(4, newProgress.getInterestRate());
            ps.setInt(5, newProgress.getCustomersPerDay());
            ps.setInt(6, newProgress.getRestaurantSize());
            ps.setInt(7, newProgress.getDaysPlayed());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ

    public Progress getProgressById(int userId){
        String query = "SELECT user_id, cash, loan, interest_rate, customers_per_day, restaurant_size, days_played FROM progress WHERE user_id = ?;";
        Progress progress = new Progress();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progress;
    }

    // UPDATE

    public void updateProgressCashById(int userId, BigDecimal cash) {
        String query = "UPDATE progress SET cash = ? WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBigDecimal(1, cash);
            ps.setInt(2, userId);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProgressLoanById(int userId, BigDecimal loan) {
        String query = "UPDATE progress SET loan = ? WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBigDecimal(1, loan);
            ps.setInt(2, userId);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProgressInterestRateById(int id, int interestRate) {
        String query = "UPDATE progress SET interest_rate = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, interestRate);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProgressCustomersPerDayById(int id, int customersPerDay) {
        String query = "UPDATE progress SET customers_per_day = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customersPerDay);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProgressRestaurantSizeById(int id, int restaurantSize) {
        String query = "UPDATE progress SET restaurant_size = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, restaurantSize);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProgressDaysPlayedById(int id, int daysPlayed) {
        String query = "UPDATE progress SET days_played = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, daysPlayed);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // DELETE

    public void deleteProgressById(int id) {
        String query = "DELETE FROM progress WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgress(Progress progress) {
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM progress WHERE user_id = ?;");
            ps.setInt(1, progress.getUserId());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
