package jek.services.system;

import jek.services.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import java.util.*;

public class DynamoDBService {
    private DynamoDbClient dynamoDbClient = null;
    private final String TABLE_NAME = "AmountInStock";
    private final String KEY_ATTRIBUTE_NAME = "UserId";
    private final ProgressService progressService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final ToppingService toppingService;

    public DynamoDBService(ProgressService progressService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.progressService = progressService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
        this.dynamoDbClient = getConnection();
        try {
            createDynamoDBTable();
        } catch (ResourceInUseException e) {
            System.out.println("Table exists: " + TABLE_NAME);
        }
    }

    private DynamoDbClient getConnection () {
        return DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    private void createDynamoDBTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName(KEY_ATTRIBUTE_NAME)
                                .attributeType(ScalarAttributeType.N)
                                .build()
                )
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName(KEY_ATTRIBUTE_NAME)
                                .keyType(KeyType.HASH)
                                .build()
                )
                .provisionedThroughput(
                        ProvisionedThroughput.builder()
                                .readCapacityUnits(5L)
                                .writeCapacityUnits(5L)
                                .build()
                )
                .tableName(TABLE_NAME)
                .build();
        dynamoDbClient.createTable(request);
        System.out.println("Created DynamoDB table");
    }

    public void saveAmountInStock() {
        HashMap<String, AttributeValue> inventoryMap = new HashMap<>();
        inventoryMap.put(
                KEY_ATTRIBUTE_NAME, AttributeValue.builder().n(String.valueOf(progressService.getActiveProgress().getUserId())).build());
        inventoryMap.put("RawIngredients", AttributeValue.builder().l(
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(1))).build(),
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(2))).build(),
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(3))).build(),
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(4))).build(),
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(5))).build(),
                AttributeValue.builder().n(String.valueOf(rawIngredientService.getRawIngredientAmountInStockById(6))).build())
                .build());
        inventoryMap.put("BasicIngredients", AttributeValue.builder().l(
                AttributeValue.builder().n(String.valueOf(basicIngredientService.getBasicIngredientAmountInStockById(1))).build(),
                AttributeValue.builder().n(String.valueOf(basicIngredientService.getBasicIngredientAmountInStockById(2))).build(),
                AttributeValue.builder().n(String.valueOf(basicIngredientService.getBasicIngredientAmountInStockById(3))).build())
                .build());
        inventoryMap.put("Toppings", AttributeValue.builder().l(
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(1))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(2))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(3))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(4))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(5))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(6))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(7))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(8))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(9))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(10))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(11))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(12))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(13))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(14))).build(),
                AttributeValue.builder().n(String.valueOf(toppingService.getToppingAmountInStockById(15))).build())
                .build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(inventoryMap)
                .build();
        dynamoDbClient.putItem(request);
    }

    public void loadAmountInStock() {
        HashMap<String, AttributeValue> loadAmountInStockMap = new HashMap<>();
        loadAmountInStockMap.put(KEY_ATTRIBUTE_NAME, AttributeValue.builder().n(String.valueOf(progressService.getActiveProgress().getUserId())).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(loadAmountInStockMap)
                .build();

        Map <String, AttributeValue> amountInStock = dynamoDbClient.getItem(request).item();
        if (amountInStock == null) {
            System.out.println("Amount in stock is empty");
        }
        else {
            List <Integer> rawIngredients = new ArrayList<>();
            amountInStock.get("RawIngredients").l().forEach(item -> rawIngredients.add(Integer.parseInt(item.n())));
            List <Integer> basicIngredients = new ArrayList<>();
            amountInStock.get("BasicIngredients").l().forEach(item -> basicIngredients.add(Integer.parseInt(item.n())));
            List <Integer> toppings = new ArrayList<>();
            amountInStock.get("Toppings").l().forEach(item -> toppings.add(Integer.parseInt(item.n())));

            setAllAmountInStock(rawIngredients, basicIngredients, toppings);
        }
    }

    public void setAllAmountInStock(List <Integer> rawIngredients, List <Integer> basicIngredients, List <Integer> toppings) {
        rawIngredientService.setAllAmountInStock(rawIngredients);
        basicIngredientService.setAllAmountInStock(basicIngredients);
        toppingService.setAllAmountInStock(toppings);
    }

    public void deleteAllAmountInStock() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();
        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);

        for (Map <String, AttributeValue> item: scanResponse.items()){
            DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(Collections.singletonMap(KEY_ATTRIBUTE_NAME, item.get(KEY_ATTRIBUTE_NAME)))
                    .build();
            dynamoDbClient.deleteItem(deleteItemRequest);
        }
        System.out.println("AmountInStock database was successfully purged!");
    }

    public void deleteUserInventory() {
        HashMap<String, AttributeValue> keyToDelete = new HashMap<>();
        keyToDelete.put(KEY_ATTRIBUTE_NAME,
                AttributeValue.builder().n(String.valueOf(progressService.getActiveProgress().getUserId()))
                        .build());
        DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(keyToDelete)
                .build();
        dynamoDbClient.deleteItem(deleteItemRequest);
        System.out.println("User inventory successfully deleted");
    }

}
