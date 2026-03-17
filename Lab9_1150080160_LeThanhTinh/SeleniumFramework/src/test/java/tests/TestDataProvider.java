package tests;

import framework.model.UserData;
import framework.utils.ExcelUtils;
import framework.utils.JsonReader;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

public class TestDataProvider {

    // ================== BÀI 3 ==================
    private static final String EXCEL_PATH = "src/test/resources/testdata/login_data.xlsx";

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(Method method) {

        List<Map<String, String>> finalData = new ArrayList<>();

        // Lấy group của test
        String[] groups = method.getAnnotation(org.testng.annotations.Test.class).groups();

        boolean isSmoke = false;

        for (String g : groups) {
            if (g.equalsIgnoreCase("smoke")) {
                isSmoke = true;
                break;
            }
        }

        if (isSmoke) {
            // chỉ đọc SmokeCases
            finalData.addAll(ExcelUtils.getSheetData(EXCEL_PATH, "SmokeCases"));
        } else {
            // đọc tất cả
            finalData.addAll(ExcelUtils.getSheetData(EXCEL_PATH, "SmokeCases"));
            finalData.addAll(ExcelUtils.getSheetData(EXCEL_PATH, "NegativeCases"));
            finalData.addAll(ExcelUtils.getSheetData(EXCEL_PATH, "BoundaryCases"));
        }

        Object[][] result = new Object[finalData.size()][1];

        for (int i = 0; i < finalData.size(); i++) {
            result[i][0] = finalData.get(i);
        }

        return result;
    }

    // ================== BÀI 4 ==================

    private static final String JSON_PATH = "src/test/resources/testdata/users.json";

    @DataProvider(name = "jsonUsers")
    public static Object[][] getUsersFromJson() {

        List<UserData> users = JsonReader.readUsers(JSON_PATH);

        Object[][] data = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }

        return data;
    }
}