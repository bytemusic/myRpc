package server;

import com.liu.model.FirstRpcModel;
import com.liu.service.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author knuslus
 */
public class HelloServer implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    @Override
    public String firstRpcMethod(FirstRpcModel FirstRpcModel) {
        logger.info("接收到服务{}", FirstRpcModel.getMessage());
        return "接收到服务id" + FirstRpcModel.getId();
    }
}
