package com.liu.rpc.core.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CommonSerializerImpl implements CommonSerializer {
    private static final Logger logger = LoggerFactory.getLogger(CommonSerializerImpl.class);
    private ObjectMapper objectMapper;

    @Override
    public byte[] serializer(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            logger.warn("CommonSerializerImpl.serializer warm", e);
            throw new RuntimeException("CommonSerializerImpl.serializer warm");
        }
    }

    @Override
    public Object reSerializer(byte[] bytes, Class<?> clazz) {
        try {
            Object readValue = objectMapper.readValue(bytes, clazz);
            //反序列化后会造成
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
