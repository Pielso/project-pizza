package jek.services.system;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDBService {

    private static DynamoDbClient dynamoDbClient;

    public DynamoDBService(){

    }

    public static DynamoDbClient getConnection(){
        if(dynamoDbClient == null){
            dynamoDbClient = DynamoDbClient.builder()
                    .region(Region.EU_NORTH_1)
                    .build();

        }
        return dynamoDbClient;
    }

    



}
