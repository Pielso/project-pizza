package jek;

import jek.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {

        if (DatabaseManager.getConnection().isValid(10)){
            System.out.println("Tja");

            Connection linkToDb = DatabaseManager.getConnection();
            // String insertIntoGames = "INSERT INTO games (title, min_players, max_players, rating) VALUES (?, ?, ?, ?);";

            try {
                DatabaseManager.dropDatabase();
                DatabaseManager.createDatabase();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}