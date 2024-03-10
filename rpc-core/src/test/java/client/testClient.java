package client;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.api.service.PartnerService;
import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.proxy.RpcClientProxy;

/**
 * 客户端测试
 * @author knuslus
 */
public class testClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 90);
        RpcClient proxyClass = rpcClientProxy.getProxyClass(RpcClient.class);
        FirstRpcModel FirstRpcModel = new FirstRpcModel(1, "www.tencent.com");
        String s = proxyClass.firstRpcMethod(FirstRpcModel);
        String trade = proxyClass.secondRpcMethod("8645634958348", 234353l);
        System.out.println(s);
        System.out.printf(trade);
        PartnerService partnerService = rpcClientProxy.getProxyClass(PartnerService.class);
        String accept = partnerService.sellerAccept("333", 44l);
        System.out.printf(accept);

    }
}
