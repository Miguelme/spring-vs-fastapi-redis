package com.example.redistest.controller;

import com.example.redistest.service.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.example.redistest.utils.RedisTestUtils.*;

@RestController
public class RedisTemplateController {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @GetMapping("/template")
    public Set<String> getAllUsingTemplate() {
        return redisTemplateService.getKeys();
    }

    @PostMapping("/template")
    public List<String> getKeysUsingBareRedisTemplate(@RequestBody List<String> keys) {
        Instant start = Instant.now();
        List<String> response = redisTemplateService.getKeys(keys);
        Instant end = Instant.now();
        System.out.println("Time spent finding by ID with redis template:" + Duration.between(start, end));
        return response;
    }

    @GetMapping("/template/{key}")
    public String getKeyUsingBareRedisTemplate(@PathVariable String key) {
        Instant start = Instant.now();
        String response = redisTemplateService.getKey(key);
        Instant end = Instant.now();
        System.out.println("Time spent finding by ID with redis template:" + Duration.between(start, end));
        return response;
    }


    @GetMapping("/template/generate/{numberToGenerate}")
    public boolean generateValuesUsingTemplate(@PathVariable int numberToGenerate) {
        generateKeyValuesUsingTemplate(numberToGenerate);
        return true;
    }


    private void generateKeyValuesUsingTemplate(int numberOfKVsToGenerate) {
        Map<String, String> generatedKeyValues = generateKeyValues(numberOfKVsToGenerate);
        redisTemplateService.saveKeys(generatedKeyValues);
    }
}
