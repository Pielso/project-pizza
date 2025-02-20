package jek;

import jek.database.DatabaseManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        if (DatabaseManager.getConnection().isValid(10)){
            System.out.println("Tja");


            // String insertIntoGames = "INSERT INTO games (title, min_players, max_players, rating) VALUES (?, ?, ?, ?);";

            try {
                DatabaseManager.dropDatabase();
                DatabaseManager.createDatabase();
                DatabaseManager.scriptRunner("src/main/java/jek/sql-scripts/insert-basic-ingredients.sql");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}