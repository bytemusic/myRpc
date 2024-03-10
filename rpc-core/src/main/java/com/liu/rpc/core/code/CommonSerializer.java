package com.liu.rpc.core.code;

public interface CommonSerializer {
    byte[] serializer(Object object);

    Object deSerializer(byte[] bytes, Class<?> clazz);


}
