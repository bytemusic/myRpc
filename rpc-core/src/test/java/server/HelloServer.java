package server;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.api.service.RpcClient;
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
        return String.valueOf(FirstRpcModel.getId());
    }
}
