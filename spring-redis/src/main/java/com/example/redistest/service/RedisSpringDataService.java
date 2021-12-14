package com.example.redistest.service;

import com.example.redistest.repo.KeyValue;
import com.example.redistest.repo.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RedisSpringDataService implements RedisService{

    @Autowired
    private KeyValueRepository keyValueRepository;

    public List<String> getKeys() {
        return StreamSupport.stream(keyValueRepository.findAll().spliterator(), true).map(KeyValue::getId).collect(Collectors.toList());
    }

    public boolean saveKey(String key, String value) {
        keyValueRepository.save(new KeyValue(key, value));
        return true;
    }

    public boolean saveKeys(Map<String, String> keyValues) {
        List<KeyValue> keyValuesToSave = keyValues.entrySet()
                        .parallelStream()
                        .map(entry -> new KeyValue(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());

        keyValueRepository.saveAll(keyValuesToSave);
        return true;
    }

    public String getKey(String key) {
        return keyValueRepository.findById(key).map(KeyValue::getValue).orElse(null);
    }
}
