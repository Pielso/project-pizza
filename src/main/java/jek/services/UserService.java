package jek.services;
import jek.models.User;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static jek.controllers.LoginController.activeUser;

public class UserService {

    public static int getUserIdByUsername(String name){
        String query = "SELECT user_id FROM users WHERE username = ?;";
        int id = 0;
        try (Connection connection = DatabaseService.getConnection()){
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

    public static void SaveNewUser(User newUser) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?);";

        try (Connection connection = DatabaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setActiveUserByUsername(String name){

        String query = "SELECT user_id, password FROM users WHERE username = ?;";

        try (Connection connection = DatabaseService.getConnection()){

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                activeUser.setUserId(rs.getInt("user_id"));
                activeUser.setUsername(name);
                activeUser.setPassword(rs.getString("password"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
