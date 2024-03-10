package server;

import com.liu.rpc.api.service.PartnerService;

/**
 * 功能描述: 商家服务
 *
 * @author knuslus
 * @date 2024/3/11 00:50
 */
public class PartnerServiceImpl implements PartnerService {
    @Override
    public String sellerAccept(String tradeId, Long sellerId) {

        return "商家已经接单";
    }
}
