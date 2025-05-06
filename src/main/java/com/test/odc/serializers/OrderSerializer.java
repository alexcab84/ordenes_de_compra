package com.test.odc.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.odc.entities.Order;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderSerializer implements Serializer<Order> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Order order) {
        try {
            return objectMapper.writeValueAsBytes(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        
    }

    @Override
    public void close() {
        
    }
}

