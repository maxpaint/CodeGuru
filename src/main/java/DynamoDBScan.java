import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Iterator;
import java.util.Map;

/**
 * Return all items in a DynamoDB Table
 * <p>
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class DynamoDBScan implements RequestHandler<Map<String, String>, String> {

    static String tableName = "<FMI1>";
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.EU_WEST_3)
            .build();
    static DynamoDB dynamoDB = new DynamoDB(client);

    private static String findAllItems() {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan();
        Iterator<Item> iterator = items.iterator();
        String itemsToReturn = "";
        while (iterator.hasNext()) {
            itemsToReturn += iterator.next().toJSONPretty();
        }

        return itemsToReturn;
    }

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        return findAllItems();
    }

}
