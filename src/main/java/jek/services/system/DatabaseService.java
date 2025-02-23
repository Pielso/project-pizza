package jek.services.system;

import jek.models.BasicIngredient;
import jek.models.RawIngredient;
import jek.models.Topping;
import jek.services.BasicIngredientService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pizza";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "+?.5griFFeLt4vla";

    private static final String dropDB = "src/main/java/jek/sql-scripts/drop-and-resurrect-database.sql";
    private static final String createDB = "src/main/java/jek/sql-scripts/create-all-tables.sql";

    //private static final String insertRawIngredients = scriptParser("src/main/java/jek/sql-scripts/insert-raw-ingredients.sql");
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
        String query = "SELECT ? FROM ?;";
        List<String> list = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, column);
            ps.setString(2, table);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(rs.getString(column));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        return list;
    }

    public static void insertToppingsAndIngredients(){
        //scriptRunner(insertRawIngredients);
        scriptRunner(insertBasicIngredients);
        scriptRunner(insertToppings);
    }

    public static void dropDatabase() {
        scriptRunner(dropDB);
    }

    public static void createDatabase() {
        scriptRunner(createDB);
    }

    private static void insertRawIngredients() {
        try (Connection connection = getConnection()){
            for (RawIngredient ingredient: RawIngredientService.createInventoryOfRawIngredients()){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO raw_ingredients (raw_ingredient_name, amount_in_stock) VALUES (?, ?);");
                preparedStatement.setString(1, ingredient.getRawIngredientName());
                preparedStatement.setInt(2, 0);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertBasicIngredients() {
        try (Connection connection = getConnection()){
            for (BasicIngredient ingredient: BasicIngredientService.createInventoryOfBasicIngredients()){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO basic_ingredients (basic_ingredient_name, amount_in_stock) VALUES (?, ?);");
                preparedStatement.setString(1, ingredient.getBasicIngredientName());
                preparedStatement.setInt(2, 0);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertToppings() {
        try (Connection connection = getConnection()){
            for (Topping topping: ToppingService.createInventoryOfToppings()){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO toppings (topping_name, amount_in_stock) VALUES (?, ?);");
                preparedStatement.setString(1, topping.getToppingName());
                preparedStatement.setInt(2, 0);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createInitialInventory() throws SQLException {
        insertRawIngredients();
        insertBasicIngredients();
        insertToppings();
    }
}
