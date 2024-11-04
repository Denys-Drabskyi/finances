package com.example.finances.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

    public static String readJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file from path: " + filePath, e);
        }
    }
}
