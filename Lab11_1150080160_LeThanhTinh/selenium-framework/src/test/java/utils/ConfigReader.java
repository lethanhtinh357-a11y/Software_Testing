package utils;

public class ConfigReader {

    public static String getUsername() {
        String username = System.getenv("SAUCEDEMO_USERNAME");

        if (username == null || username.isEmpty()) {
            System.out.println("⚠️ Username từ ENV bị null!");
            return "";
        }

        return username;
    }

    public static String getPassword() {
        String password = System.getenv("SAUCEDEMO_PASSWORD");

        if (password == null || password.isEmpty()) {
            System.out.println("⚠️ Password từ ENV bị null!");
            return "";
        }

        return password;
    }
}