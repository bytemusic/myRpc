package com.liu.rpc.core.code;

public interface CommonSerializer {
    byte[] serializer(Object object);

    Object reSerializer(byte[] bytes, Class<?> clazz);


}
