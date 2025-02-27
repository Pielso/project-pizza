package jek.repositories;

import jek.models.User;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {
    private final DatabaseService databaseService;

    public UserRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void createUser(User user) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public int getUserIdByUsername(String name){
        String query = "SELECT user_id FROM users WHERE username = ?;";
        int id = 0;
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                id = rs.getInt("user_id");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public User getUserByUsername(String name){
        User user = new User();
        String query = "SELECT user_id, username, password FROM users WHERE username = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List <String> getListOfAllUsernames() throws SQLException {
        return databaseService.columnToList("username", "users");
    }


    public User getUserById(int userId){
        User user = new User();
        String query = "SELECT user_id, username, password FROM users WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // UPDATE

    public void updateUserById(int userId, String name, String password){
        String query = "UPDATE users SET username = ?, password = ? WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3,userId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE

    public void deleteUserById(int userId){
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User user){
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
