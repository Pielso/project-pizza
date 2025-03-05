package jek.services.system;

import jek.services.BasicIngredientService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveAndLoadService {
    private final DynamoDBService dynamoDBService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final ToppingService toppingService;

    public SaveAndLoadService(DynamoDBService dynamoDBService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.dynamoDBService = dynamoDBService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }

    public void saveAmountInStock() {
        dynamoDBService.saveAmountInStock();
    }

    public void loadAmountInStock() {
        dynamoDBService.loadAmountInStock();
    }

    public void createEmptyInventory() {
        toppingService.createAllToppings();
        basicIngredientService.createAllBasicIngredients();
        rawIngredientService.createAllRawIngredients();
    }

    /**
     * <h3>Part of resetting all amountInStock between player logouts</h3>
     * <h5>Used in LoginController & PizzaGameController</h5>
     */
    public void resetAmountInStock() {
        List <Integer> zeroRawIngredients = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        List <Integer> zeroBasicIngredients = new ArrayList<>(Arrays.asList(0, 0, 0));
        List <Integer> zeroToppings = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        dynamoDBService.setAllAmountInStock(zeroRawIngredients, zeroBasicIngredients, zeroToppings);
    }

    public void dropInventory() {
        rawIngredientService.deleteAllRawIngredients();
        basicIngredientService.deleteAllBasicIngredients();
        toppingService.deleteAllToppings();
    }

    public void dropAllAmountInStockInDynamoDB() {
        dynamoDBService.deleteAllAmountInStock();
    }
}
