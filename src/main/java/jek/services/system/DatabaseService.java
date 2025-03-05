package jek.services.system;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pizza";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "+?.5griFFeLt4vla";
    private static final String dropDB = "src/main/java/jek/sql-scripts/drop-and-resurrect-database.sql";
    private static final String createDB = "src/main/java/jek/sql-scripts/create-all-tables.sql";

    public DatabaseService() {

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void scriptRunner(String path) {
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

    public void dropDatabase() {
        scriptRunner(dropDB);
    }

    public void createDatabase() {
        scriptRunner(createDB);
    }

}
