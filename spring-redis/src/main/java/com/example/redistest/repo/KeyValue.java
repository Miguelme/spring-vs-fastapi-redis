package com.example.redistest.repo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@RedisHash("KeyValue")
public class KeyValue implements Serializable {

    private String id;
    private String value;

    @Override
    public String toString() {
        return "KeyValue{" + "id='" + id + '\'' + ", value='" + value + '}';
    }
}