package com.liu.rpc.core.server;

import com.liu.rpc.core.serivce.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 服务端接收请求
 *
 * @author knuslus
 */
public class SocketServer implements RpcServer{
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
    private ServiceProvider serviceProvider;

    private RequestHandle requestHandle;

    public SocketServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    @Override
    public void start(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：{}", socket.getInetAddress());
                threadPool.execute(new RequestHandleThread(socket, requestHandle, serviceProvider));
            }
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }


}
