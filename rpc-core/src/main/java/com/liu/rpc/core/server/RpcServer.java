package com.liu.rpc.core.server;

import com.liu.rpc.core.serivce.ServiceRegister;
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
public class RpcServer {
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
    private final ExecutorService threadPool;
    private final ServiceRegister serviceRegister;
    private RequestHandle requestHandle = new RequestHandle();
    private static final int corePoolSize = 5;
    private static final int maximumPoolSize = 50;
    private static final int  keepAliveTime = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;

    public RpcServer(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：{}", socket.getInetAddress());
                threadPool.execute(new RequestHandleThread(socket, requestHandle, serviceRegister));
            }
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }


}
