package framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance;
    private Properties props = new Properties();

    private ConfigReader() {
        try {
            //  LẤY ENV (mặc định dev)
            String env = System.getProperty("env", "dev");

            //  ĐÚNG PATH TRONG resources/config/
            String fileName = "config/config-" + env + ".properties";

            System.out.println("===== CONFIG =====");
            System.out.println("Đang dùng môi trường: " + env);

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (is == null) {
                throw new RuntimeException("❌ Không tìm thấy file: " + fileName);
            }

            props.load(is);

            // 🔥 LOG RA để chấm điểm
            System.out.println("Base URL: " + props.getProperty("base.url"));
            System.out.println("Explicit Wait: " + props.getProperty("explicit.wait"));
            System.out.println("Retry Count: " + props.getProperty("retry.count"));

            System.out.println("===================");

        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi load config", e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    // ===== GETTERS =====

    public String getBaseUrl() {
        return props.getProperty("base.url");
    }

    public int getExplicitWait() {
        return Integer.parseInt(props.getProperty("explicit.wait", "10"));
    }

    public String getBrowser() {
        return props.getProperty("browser", "chrome");
    }

    //
    public int getRetryCount() {
        return Integer.parseInt(props.getProperty("retry.count", "0"));
    }
}