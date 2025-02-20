package jek;

import jek.database.DatabaseManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        if (DatabaseManager.getConnection().isValid(10)){
            System.out.println("Tja");

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