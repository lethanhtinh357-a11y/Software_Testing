package tests;

import framework.base.BaseTest;
import framework.utils.TestDataFactory;
import org.testng.annotations.Test;

import java.util.Map;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutWithRandomData() {

        Map<String, String> data = TestDataFactory.randomCheckoutData();

        System.out.println("First Name: " + data.get("firstName"));
        System.out.println("Last Name: " + data.get("lastName"));
        System.out.println("Postal Code: " + data.get("postalCode"));

    }
}