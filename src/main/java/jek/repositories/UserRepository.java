package jek.repositories;

import jek.models.RawIngredient;
import jek.models.User;
import jek.services.system.DatabaseService;
import software.amazon.awssdk.services.dynamodb.endpoints.internal.Value;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {
    private DatabaseService databaseService;

    public UserRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

    public void SaveNewUser(User newUser) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());

            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

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

    public List <String> returnListOfUsernames() throws SQLException {
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

    public void UpdateUserById(int userId, String name, String password){
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




}
