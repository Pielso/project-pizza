package jek.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pizza";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "+?.5griFFeLt4vla";
    private static final String dropDB = "src/main/java/jek/sql-scripts/drop-and-resurrect-database.sql";
    private static final String createDB = "src/main/java/jek/sql-scripts/create-all-tables.sql";
    private static final String insertRawIngredients = "src/main/java/jek/sql-scripts/insert-raw-ingredients.sql";
    private static final String insertBasicIngredients = "src/main/java/jek/sql-scripts/insert-basic-ingredients.sql";
    private static final String insertToppings = "src/main/java/jek/sql-scripts/insert-toppings.sql";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void scriptRunner(String path) {
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

    public static List <String> columnToList(String column, String table) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + column + " FROM " + table);
        List<String> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getString(column));
        }
        return list;
    }

    public static void insertToppingsAndIngredients(){
        scriptRunner(insertRawIngredients);
        scriptRunner(insertBasicIngredients);
        scriptRunner(insertToppings);
    }

    public static void dropDatabase() throws SQLException {
        scriptRunner(dropDB);
    }

    public static void createDatabase() throws SQLException {
        scriptRunner(createDB);
    }

}
