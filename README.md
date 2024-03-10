## 自己写一个dubbo(myRpc)
### `dev_1_1_0 完成可以注册一个服务的rpc`
1. 首先需要服务提供者(sever端)将service注册到注册中心，然后服务消费者(client)调用服务提供者。
2. 服务提供者定义一个register方法（参数是服务名，端口），先建立Socket连接，建立连接后，处于待机状态，等到有客户端请求打过来（socket#accept不为null），然后调用线程池的execute方法执行具体的注册事务。
WorkThread实现Runnable接口，具体执行事务在run方法里。
   1. 先获取socket类的输入输出流，获取输入流，转化为请求类，调用请求类的getClass().getMethod()方法，传入方法名称和方法参数，生成Method类。
   2. 调用Method类的invoke方法，传参service类和请求类的请求参数，返回一个Object类；调用objectOutputStream.flush();刷新内存

3. 定义一个服务消费者（Client）代理类，获取消费类的代理对象（代理消费类的getProxyClass方法），调用消费类的方法，然后会进去代理消费类的invoke方法
   1. invoke方法会封装RpcRequest类（传参接口名称，方法名称，方法参数），生成请求类，调用请求类的请求方法
4. 定义一个在RpcService定义Rpc请求方法，首先建立Socket连接，请求数据 
5. 定义一个代理方法，向服务端发送请求 
6. 创建一个线程池，处理多个请求
