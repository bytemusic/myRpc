package server;

import com.liu.model.FirstRpcModel;
import com.liu.service.FirstRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端对服务方法的具体实现，
 * @author knuslus
 */
public class FirstRpcServiceImpl implements FirstRpcService {
    private static final Logger logger = LoggerFactory.getLogger(FirstRpcServiceImpl.class);

    @Override
    public String firstRpcMethod(FirstRpcModel FirstRpcModel) {
        String name = "";
        if (FirstRpcModel.getMessage().equals("www.baidu.com")) {
            name = "百度";
        }
        logger.info("请求网站:{}的中文名称为{}", FirstRpcModel.getUrl(), name);
        return "服务id为" + FirstRpcModel.getId() + "请求网站的中文名称为" + name;
    }
}
