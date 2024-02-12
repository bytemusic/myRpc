package com.liu.rpc.core.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.core.exception.RpcCode;
import com.liu.rpc.core.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CommonSerializerImpl implements CommonSerializer {
    private static final Logger logger = LoggerFactory.getLogger(CommonSerializerImpl.class);
    /**
     * 不实例化会空指针
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serializer(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            logger.warn("CommonSerializerImpl.serializer warm {}", e.getMessage());
            throw new RpcException(RpcCode.SERIALIZER_ERROR, "CommonSerializerImpl.serializer warm");
        }
    }

    @Override
    public Object deSerializer(byte[] bytes, Class<?> clazz) {
        try {
            Object readValue = objectMapper.readValue(bytes, clazz);
            //反序列化后会造成不是原来的Object类型
            if (readValue instanceof RpcRequest) {
                return readValue;
            }
        } catch (IOException e) {
            logger.warn("CommonSerializerImpl.deSerializer error {}", e.getMessage());
            throw new RpcException(RpcCode.SERIALIZER_ERROR, RpcCode.SERIALIZER_ERROR.getDesc());
        }
        return null;
    }

    /**
     * rpcRequest有一个类型是Object,jackson反序列化后可能会造成Object类型反序列化失败
     * 可以把参数从Object改为String，
     * 或者根据参数类型Class<T>作区分，自定义反序列化逻辑
     * @param object
     * @return
     */
    private Object handleObject(Object object) throws IOException {

        RpcRequest rpcRequest = (RpcRequest) object;
        Class paramType = rpcRequest.getParamType();
        for (int i = 0; i < rpcRequest.getParam().length; i++) {
            if (!rpcRequest.getParamType().isAssignableFrom(rpcRequest.getParam()[i].getClass())) {
                //自定义反序列化逻辑
                byte[] bytes = objectMapper.writeValueAsBytes(rpcRequest.getParam()[i]);
                rpcRequest.getParam()[i] = objectMapper.readValue(bytes, RpcRequest.class);
            }
        }
        return rpcRequest;
    }
}
