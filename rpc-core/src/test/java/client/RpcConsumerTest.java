package client;

import com.liu.model.FirstRpcModel;
import com.liu.proxy.RpcConsumerProxy;
import com.liu.service.FirstRpcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端测试
 * @author knuslus
 */
public class RpcConsumerTest {
    private static final String ip = "127.0.0.1";
    private static final int port = 90;
    private static final Logger logger = LoggerFactory.getLogger(RpcConsumerTest.class);
    public static void main(String[] args) {
        RpcConsumerProxy rpcConsumerProxy = new RpcConsumerProxy(ip, port);
        FirstRpcService proxyClass = rpcConsumerProxy.getProxyClass(FirstRpcService.class);
        logger.info("服务消费者：{} 调用方法firstRpcMethod", proxyClass);
        String message = proxyClass.firstRpcMethod(new FirstRpcModel(1, "www.baidu.com"));
        if (StringUtils.isNotBlank(message)) {
            logger.info("message :{}", message);
        } else {
            logger.info("message is null");
        }



    }
}
