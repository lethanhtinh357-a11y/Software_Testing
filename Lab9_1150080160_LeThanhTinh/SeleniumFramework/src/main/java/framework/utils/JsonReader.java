package framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.model.UserData;

import java.io.File;
import java.util.List;

public class JsonReader {

    public static List<UserData> readUsers(String filePath) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<UserData>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc JSON", e);
        }
    }
}