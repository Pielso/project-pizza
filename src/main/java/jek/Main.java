package jek;

import jek.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        if (DatabaseManager.getConnection().isValid(10)){
            System.out.println("Tja");


            Connection linkToDb = DatabaseManager.getConnection();
            String insertIntoGames = "INSERT INTO games (title, min_players, max_players, rating) VALUES (?, ?, ?, ?);";

            try {
                DatabaseManager.dropDatabase();
                DatabaseManager.createDatabase();
//               PreparedStatement sqlIN = linkToDb.prepareStatement(insertIntoGames);
//               sqlIN.setString(1, "1830");
//               sqlIN.setInt(2, 3);
//               sqlIN.setInt(3, 6);
//               sqlIN.setInt(4, 10);
//               sqlIN.execute();
//               sqlIN.close();
//               linkToDb.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}