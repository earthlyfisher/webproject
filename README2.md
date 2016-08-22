#单例
##内部类实现
```java
/**
 * 这些情况下就不用自己再来进行同步控制了。这些情况包括：
 * 
 * 1.由静态初始化器（在静态字段上或static{}块中的初始化器）初始化数据时
 * 
 * 2.访问final字段时
 * 
 * 3.在创建线程之前创建对象时
 * 
 * 4.线程可以看见它将要处理的对象时
 * 
 * @author earthlyfisher
 *
 */
public class SingleInstance {

	public SingleInstance() {
		super();
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
	 */
	private static class InstanseHolder {
		static{
			System.out.println("内部类加载");
		}
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		public static final SingleInstance instanse = new SingleInstance();
	}

	public static SingleInstance getInstance() {
		/**
		 * 当getInstance方法第一次被调用的时候，它第一次读取SingletonHolder.instance，
		 * 导致SingletonHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静态域，从而创建Singleton的实例，
		 * 由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
		 */
		return InstanseHolder.instanse;
	}
}
```
##枚举

```java
/**
 * 1.线程安全，通过类加载器保证线程安全。
 * 2.抗序列化问题：枚举类自己实现了readResolve（）方法，所以抗序列化，这个方法是当前类自己实现的（解决）
 * @author earthlyfisher
 *
 */
public enum SingleInstanceByEnum {

	instance;

	/**
	 * 单例可以有自己的操作
	 */
	public void singletonOperation() {
		System.out.println("hello world");
	}
}
```
#一些注意点
##获取JVM版本是32还是64
**可以检查某些系统属性如 `sun.arch.data.model` 或 `os.arch` 来获取该信息**
 即通过系统属性获取`System.getProperties()`
##switch case
`switch`支持`byte`,`short`,`int`,`boolean`,`char`,从1.7开始支持`String`,但这仅仅是一个语法糖。
内部实现在 switch 中使用字符串的 hashcode.但不支持`long`.
##精度问题
```java
public void testMulti() {
		System.out.println(3 * 0.1 == 0.3);// false:0.30000000000000004!=0.3,精度问题
	}
```
##direct-buffer,non-direct-buffer

```java
     /**
	 * Direct vs Non-direct vs MappedByteBuffer in Java: Direct buffers are
	 * allocated outside heap and they are not in control of Garbage Collection
	 * while non-direct buffers are simply a wrapper around byte arrays, located
	 * inside heap. Memory mapped files can be accessed by using
	 * MappedByteBuffer, which is also a direct buffer. One more thing to
	 * remember is that default order of bytes in ByteBuffer is BIG_ENDIAN,
	 * which means the bytes of a multibyte value are ordered from most
	 * significant to least significant.
	 * 
	 * Key advantage of Memory Mapped File is that operating system takes care
	 * of reading and writing and even if your program crashed just after
	 * writing into memory. OS will take care of writing content to File.
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	@SuppressWarnings("resource")
	@Test
	public void testIo() throws IOException {
		int count = 10485760; // 10 MB

		RandomAccessFile memoryMappedFile = new RandomAccessFile("D:\\test\\largeFile.txt", "rw");

		// Mapping a file into memory
		MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, count * 100);

		// Writing into Memory Mapped File
		for (int i = 0; i < count; i++) {
			out.put((byte) 'A');
		}

		System.out.println("Writing to Memory Mapped File is completed");

		// reading from memory file in Java
		for (int i = 0; i < 10; i++) {
			System.out.print((char) out.get(i));
		}
		System.out.println();
		System.out.println("Reading from Memory Mapped File is completed");

	}
```
##一个+运算符
```java
public static void main(String[] args) {
		byte a = 127;
		byte b = 127;
		b = a + b; // error : cannot convert from int to byte
		b += a; // ok:+= 隐式的将加操作的结果类型强制转换为持有结果的类型
	}
```
##ConcurrentModificationException while Iterating over ArrayList
![](WebContent/md/ConcurrentModificationException while Iterating over ArrayList.png)

##3 ways to find duplicate elements in an array Java
![](WebContent/md/3 ways to find duplicate elements in an array Java.png)

##Difference between Direct and Mapped ByteBuffer in Java
![](WebContent/md/Difference between Direct and Mapped ByteBuffer in Java.jpg)
##异常的分类
所有异常都是Throwable的子类，根据使用类别分为Exception和Error，Error是由于系统问题造成的，如内存空间不足等，而Exception是具体的异常的父类，根据编译器和运行期分为受检查异常和运行时异常，具体的集成结构图如下:
![](WebContent/md/exception  navigator.png)
##Map
`HashMap`通过hashtable实现key-value的存储，按照key的hashCode存储，可以有null key
`TreeMap`是有序的，通过key的Comparable比较器实现key-value的有序存储
`LinkedHashMap`也是有序的，也是通过key的hashCode存储，但是遍历是增加了有序属性，即与加入的顺序保持一致

##LinkedList与Queue
```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```
可知`LinkedList`实现了`Deque`，而`Deque`是丰富了父接口`Queue`，
`Queue`是Collection接口的子类，`Collection`实现了`Iterable`,所以是可遍历的。
而`Map`没有实现`Iterable`，但是通过一系列方法如`entrySet()`等转换成集合类`Set`来实现遍历功能.

##序列化和反序列化
`序列化可以将一个java对象以二进制流的方式在网络中传输并且可以被持久化到数据库、文件系统中，反序列化则是可以把之前持久化在数据库或文件系统中的二进制数据以流的方式读取出来重新构造成一个和之前相同内容的java对象。`
###示例
```java
ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			Customer customer = new Customer();
			customer.setId(1);
			customer.setName("Gavin");
			customer.setSalt("00000000011111111");
			File file = new File("D://customer.obj");
			
			//Serialization
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(customer);
			
			//deserialization
			in = new ObjectInputStream(new FileInputStream(file));
			Customer inCustomer = (Customer) in.readObject();
			System.out.println(inCustomer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("happened to deserialization");
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
```
### 静态long类型常量`serialVersionUID`的作用
显示的设置`serialVersionUID`值就可以保证版本的兼容性，如果你在类中写上了这个值，就算类变动了，它反序列化的时候也能和文件中的原值匹配上。而新增的值则会设置成零值，删除的值则不会显示。
###summary
1. `序列化是深复制，反序列化还原后的对象地址与原来的的地址不同.`
2.  `static`,`transient`修饰的变量不能被序列化
##spring要点
###DI,IOC
DI(依赖注入)是spring的核心功能之一。DI能够删除任何特定的依赖于别的类或第三方接口的类，并且能够在初始化构造时加载要依赖的类。DI的优点是你可以依赖类的实现而并不需要更改你的代码。你甚至可以在接口不变的条件下重写依赖的实现而不用改变你的编码，即面向接口的编程。
###Spring beans定义和依赖实现方式
####XML 文本文件
通过XML文本文件构造Spring beans和依赖缺失了编译时的类型检查，任何问题比如构造器参数的类型错误，甚至是构造器错误的参数只有在application context在运行时构造时才会检查。
####使用注解
通过配置注解扫描的根包，并且在bean上使用注解@Service等标示他是一个bean
```java
@Autowired
private UserService userService;
	
@Service("userService")
public class UserServiceImpl implements UserService
```
```xml
   <!-- 开启注解配置 即Autowried -->  
    <context:annotation-config/>  
    
    <!--    使用自动注入的时候要  添加他来扫描bean之后才能在使用的时候   -->
    <context:component-scan base-package="com.wyp.module.service,com.wyp.module.dao"/>  
```
###spring mvc
####Configuring a servlet container
通过`org.springframework.web.servlet.DispatcherServlet`管理，在`web.xml`里面配置：
```
<servlet>
        <servlet-name>rest</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>  
	      <param-name>contextConfigLocation</param-name>  
          <param-value>/WEB-INF/rest-servlet.xml</param-value>  
	</init-param>
	<!-- 这个配置文件在容器启动的时候 就加载 -->
    <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>rest</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
如果
```
<init-param>  
	      <param-name>contextConfigLocation</param-name>  
          <param-value>/WEB-INF/rest-servlet.xml</param-value>  
	</init-param>
```
不指定，则默认会加载该文件` WEB-INF/[servlet-name]-servlet.xml`.
##代理模式和装饰模式的区别
两者是相似，但是有些细微的区别，主要是方法粒度不一样，装饰模式一般只是对特定行为方法增加额外职责；而代理模式则是所有方法增加新职责，而且 这个新职责是一个方面或一系列的，并且装饰模式可以有装饰链。


















