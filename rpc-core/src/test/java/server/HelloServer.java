package server;

import com.liu.model.FirstRpcModel;
import com.liu.service.FirstRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端对服务的具体实现，
 * @author knuslus
 */
public class HelloServer implements FirstRpcService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    @Override
    public String firstRpcMethod(FirstRpcModel FirstRpcModel) {
        logger.info("服务端对服务的方法具体实现是{}", FirstRpcModel.getMessage());
        return "服务id为" + FirstRpcModel.getId();
    }
}
