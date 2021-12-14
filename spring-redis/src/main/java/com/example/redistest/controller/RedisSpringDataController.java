package com.example.redistest.controller;

import com.example.redistest.service.RedisSpringDataService;
import com.example.redistest.utils.RedisTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@RestController
public class RedisSpringDataController {

    @Autowired
    private RedisSpringDataService redisSpringDataService;

    @GetMapping("/repo")
    public Iterable<String> getAll() {
        return redisSpringDataService.getKeys();
    }


    @GetMapping("/repo/{key}")
    public String getKey(@PathVariable String key) {
        Instant start = Instant.now();
        String response = redisSpringDataService.getKey(key);
        Instant end = Instant.now();

        System.out.println("Time spent finding by ID with repo:" + Duration.between(start, end));
        return response;
    }

    @GetMapping("/repo/generate/{numberToGenerate}")
    public boolean generateValues(@PathVariable int numberToGenerate) {
        generateKeyValues(numberToGenerate);
        return true;
    }


    private void generateKeyValues(int numberOfKVsToGenerate) {
        Map<String, String> generatedKeyValues = RedisTestUtils.generateKeyValues(numberOfKVsToGenerate);
        redisSpringDataService.saveKeys(generatedKeyValues);
    }
}
