## 自己写一个dubbo(myRpc)
### `dev_1_1_0 完成可以注册一个服务的rpc`
1. 假设已知请求方法，请求地址，请求端口。服务提供者也知道消费端需要消费的方法。
首先需要服务提供者(sever端)将service注册到注册中心，然后服务消费者(client)调用服务提供者。
2. 服务提供者定义一个register方法（参数是服务名，端口），register方法先建立Socket连接，建立连接后，处于待机状态，
等到有客户端请求打过来（socket#accept不为null），然后调用线程池的execute方法执行具体的注册事务。
WorkThread实现Runnable接口，具体执行事务在run方法里。
   1. 先获取socket类的输入流，转化为请求类，调用传递的service的getClass().getMethod()方法，传入方法名称和方法参数，生成Method类。
   调用Method类的invoke方法，传参service类和请求类的请求参数，返回一个Object类；调用objectOutputStream.flush();刷新内存

3. 定义一个服务消费者（Client）代理类，获取消费类的代理对象（代理消费类的getProxyClass方法），代理对象调用消费类想要调用的方法； 
然后会进去消费类代理对象的invoke方法
   1. invoke方法会封装RpcRequest类（传参接口名称，方法名称，方法参数），实例化一个消费类，调用消费类实例的发送请求方法sendRequest
   2. 请求方法sendRequest主要做以下操作，建立Socket连接，调用Socket的输出流，转化为ObjectOutputStream；
   调用ObjectOutputStream的writeObject方法，将RpcRequest传输给服务端。 调用ObjectOutputStream的flush方法
   3. 调用Socket的输入流，获取服务端的响应，转化为ObjectInputStream；调用ObjectInputStream的readObject方法，返回。
4. 遇到难题
   1. 只建立连接，中间处理流程报错，调用远程方法报错.服务端找不到客户端调用的方法,应该是请求参数封装有问题。确实是,在本次提交解决，RpcConsumerProxy
   2. java.net.SocketException: Broken pipe (Write failed) 服务端没有把返回结果写入输出流，返回给请求端。
      1. 第一次请求端传参方法名称为toSting方法（RpcRequest(ip=127.0.0.1, interfaceName=java.lang.Object, methodName=toString, param=null, paramType=[])）,
      导致服务端处理不了，客户端再次请求，服务端判断socket是否正在建立连接，此时莫名奇妙，连接断开.
      解决了上面的问题，一是ObjectInputStream的位置，二是RpcConsumerProxy调用sendRequest的返回参数

