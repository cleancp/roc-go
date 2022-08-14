### 文件

https://cyc2018.github.io/CS-Notes/#/notes/Java%20IO?id=%e4%b8%80%e3%80%81%e6%a6%82%e8%a7%88

InputStream 是抽象组件；

FileInputStream 是 InputStream 的子类，属于具体组件，提供了字节流的输入操作；

FilterInputStream 属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如 BufferedInputStream 为 FileInputStream 提供缓存的功能。


```
//实例化一个具有缓存功能的字节流对象时，
//只需要在 FileInputStream 对象上再套一层 BufferedInputStream 对象即可。
FileInputStream fileInputStream = new FileInputStream(filePath);
BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
```
DataInputStream 装饰者提供了对更多数据类型进行输入的操作，比如 int、double 等基本类型。

编码就是把字符转换为字节，而解码是把字节重新组合成字符。
Writer：将字符流编码为字节流
Reader：将字节流解码为字符流

序列化就是将一个对象转换成字节序列，方便存储和传输。
不会对静态变量进行序列化，因为序列化只是保存对象的状态，静态变量属于类的状态。
类中所有属性类型都要序列化，否则会报错,transient修饰不会被序列化

### 网络
InetAddress：用于表示网络上的硬件资源，即 IP 地址；

URL：统一资源定位符；

Sockets：使用 TCP 协议实现网络通信；

Datagram：使用 UDP 协议实现网络通信。