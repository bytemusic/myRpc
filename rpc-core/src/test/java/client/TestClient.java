package client;

import com.liu.model.FirstRpcModel;
import com.liu.proxy.RpcConsumerProxy;
import com.liu.service.RpcConsumer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端测试
 * @author knuslus
 */
public class TestClient {
    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);
    public static void main(String[] args) {
        RpcConsumerProxy rpcConsumerProxy = new RpcConsumerProxy("127.0.0.1", 90);
        RpcConsumer proxyClass = rpcConsumerProxy.getProxyClass(RpcConsumer.class);
        FirstRpcModel firstRpcModel = new FirstRpcModel(1, "发送第一条信息");
        logger.info("before testClient.proxyClass.firstRpcModel");
        String message = proxyClass.firstRpcMethod(firstRpcModel);
        if (StringUtils.isNotBlank(message)) {
            logger.info("message :{}", message);
        } else {
            logger.info("message is null");
        }



    }
}
