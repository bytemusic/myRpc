package com.liu.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 服务提供者
 *
 * @author knuslus
 */
public class RpcProvider {
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcProvider.class);

    public RpcProvider() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    /**
     * 服务提供者注册服务
     * @param service 注册服务名
     * @param port 端口
     */
    public void register(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：{}", socket.getInetAddress());
                threadPool.execute(new WorkThread(service,socket));
            }
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }


}
