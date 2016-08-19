package test;

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
