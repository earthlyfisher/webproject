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


