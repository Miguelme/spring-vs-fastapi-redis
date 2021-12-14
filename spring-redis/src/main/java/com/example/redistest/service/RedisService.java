package com.example.redistest.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    public Collection<String> getKeys();
    public String getKey(String key);

    public boolean saveKey(String key, String value);
    public boolean saveKeys(Map<String, String> generatedKeyValues);
}
