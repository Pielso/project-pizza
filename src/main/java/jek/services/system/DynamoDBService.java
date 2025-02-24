package jek.services.system;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDBService {

//    public static void main(String[] args) {
//        var ddb = getConnection();
//        System.out.println("Dynamo works");
//    }

    public DynamoDBService(){

    }

    public static DynamoDbClient getConnection(){
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
        return ddb;
    }




}
