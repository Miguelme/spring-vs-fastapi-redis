package com.example.redistest.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

public class RedisTestUtils {

    public static String generateId() {
        return "template:" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Map<String, String> generateKeyValues(int numberOfKeyValues) {
        Map<String, String> generatedKeyValues = new HashMap<>();
        IntStream.range(0, numberOfKeyValues)
                .parallel()
                .forEach(x -> generatedKeyValues.put(generateId(), generateName()));
        return generatedKeyValues;
    }
}
