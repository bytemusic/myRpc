# myRpc
## 自己写一个rpc
### `dev_1_1_0 完成可以注册一个服务的rpc`
1. 首先需要服务提供者(sever端)将service注册到注册中心，然后服务消费者(client)调用服务提供者。
2. 服务提供者定义一个register方法（参数是服务名，IP，端口），先建立Socket连接，然后调用线程池的execute方法执行具体的注册事务。

3. 定义一个服务消费者（Client）代理类，获取消费类的代理对象，调用消费类的方法 
4. 定义一个在RpcService定义Rpc请求方法，首先建立Socket连接，请求数据 
5. 定义一个代理方法，向服务端发送请求 
6. 创建一个线程池，处理多个请求
