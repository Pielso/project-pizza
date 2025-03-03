package jek.services.system;

import jek.services.BasicIngredientService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveAndLoadService {
    List <Integer> zeroRawIngredients = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0));
    List <Integer> zeroBasicIngredients = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
    List <Integer> zeroToppings = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private final DynamoDBService dynamoDBService;
    private final DatabaseService databaseService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final ToppingService toppingService;

    public SaveAndLoadService(DynamoDBService dynamoDBService, DatabaseService databaseService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.dynamoDBService = dynamoDBService;
        this.databaseService = databaseService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }

    public void saveAmountInStock() throws SQLException, InterruptedException {
        dynamoDBService.saveAmountInStock();
    }

    public void loadAmountInStock() throws SQLException, InterruptedException {
        dynamoDBService.loadAmountInStock();
    }

    public void dropAmountInStock() throws SQLException, InterruptedException {
        dynamoDBService.setAllAmountInStock(zeroRawIngredients, zeroBasicIngredients, zeroToppings);
    }

    public void dropAllAmountInStockInDynamoDB() {
        dynamoDBService.deleteAllAmountInStock();
    }

    public void createEmptyInventory() throws SQLException {
        databaseService.createInventory(rawIngredientService, basicIngredientService, toppingService);
    }

    public void dropInventory() throws SQLException {
        rawIngredientService.deleteAllRawIngredients();
        basicIngredientService.deleteAllBasicIngredients();
        toppingService.deleteAllToppings();
    }

}
