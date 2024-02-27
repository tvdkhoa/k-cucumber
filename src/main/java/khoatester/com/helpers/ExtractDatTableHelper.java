package khoatester.com.helpers;
import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ExtractDatTableHelper {
    public static Map<String, String> extractDataTableToMap(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        List<String> headers = data.get(0);
        List<String> values = data.get(1);
        Map<String, String> expectedMetadata = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String column = headers.get(i);
            String value = values.get(i);
            expectedMetadata.put(column, value);
        }
        return expectedMetadata;
    }

    public static List<Map<String, String>> extractDataTableToArrayListMap(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        List<String> headers = data.get(0);
        List<Map<String, String>> mapArray = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), data.get(i).get(j));
            }
            mapArray.add(rowMap);
        }
        return mapArray;
    }

    public static List<String> extractDataTableToArray(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        List<String> extractedData = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            extractedData.add(data.get(i).get(0));
        }
        return extractedData;
    }

    public static String extractPlusTodayToNumber(String str) {
        // Updated to handle the optional + or - signs and parse the number
        String matches = str.replaceAll("[^\\d-+]", "");
        return matches.isEmpty() ? "" : matches;
    }
}
