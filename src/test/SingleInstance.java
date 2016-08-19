package test;

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
