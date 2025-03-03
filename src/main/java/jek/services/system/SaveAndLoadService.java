package jek.services.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveAndLoadService {
    List <Integer> zeroRawIngredients = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0));
    List <Integer> zeroBasicIngredients = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
    List <Integer> zeroToppings = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private final DynamoDBService dynamoDBService;

    public SaveAndLoadService(DynamoDBService dynamoDBService) {
        this.dynamoDBService = dynamoDBService;
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

}
