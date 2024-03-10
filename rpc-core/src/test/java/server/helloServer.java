package server;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.api.service.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author knuslus
 */
public class helloServer implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(helloServer.class);
    @Override
    public String firstRpcMethod(FirstRpcModel FirstRpcModel) {
        logger.info("接收到服务{}", FirstRpcModel.getMessage());
        if (FirstRpcModel.getUrl().equals("www.tencent.com")) {
            logger.info("这是腾讯的网站");
            return FirstRpcModel.getUrl() + "这是腾讯的网站";
        }else {
            logger.info("暂时无法解析");
            return FirstRpcModel.getUrl() + "暂时无法解析";
        }
    }

    @Override
    public String  secondRpcMethod(String tradeId, long sellerId) {
        logger.info("订单id:{}, 商户id:{}",tradeId, sellerId);
        return "这是一笔医保订单";
    }
}
