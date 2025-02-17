package jek.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/certlimit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "+?.5griFFeLt4vla";
    private static final String dropDB = "src/main/java/jek/sql-scripts/drop-database.sql";
    private static final String createDB = "src/main/java/jek/sql-scripts/create-all-tables.sql";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void scriptRunner(String path) throws SQLException {
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();

            String script = new String(Files.readAllBytes(Paths.get(path)));

            String[] sqlStatements = script.split(";");

            for (String query: sqlStatements){
                query = query.trim();
                if (!query.isEmpty()){
                    statement.execute(query);
                }
            }

        } catch (SQLException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void dropDatabase() throws SQLException {
        scriptRunner(dropDB);
    }

    public static void createDatabase() throws SQLException {
        scriptRunner(createDB);
    }

}
