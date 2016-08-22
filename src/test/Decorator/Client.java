package test.Decorator;

/**
 * 装饰器模式：
 * 1.装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案。
 * 2.装饰模式通过创建一个包装对象，也就是装饰，来包裹真实的对象。
 * 3.装饰模式以对客户端透明的方式动态地给一个对象附加上更多的责任。换言之，客户端并不会觉得对象在装饰前和装饰后有什么不同。
 * 4.装饰模式可以在不创造更多子类的情况下，将对象的功能加以扩展。
 * 5.装饰模式把客户端的调用委派到被装饰类。装饰模式的关键在于这种扩展是完全透明的。
 * 6.装饰模式作用将公共逻辑织入到具体的应用代码中，起到了解耦的作用，又应用方法的主动调用变成了公共代码的主动加入。
 * 
 * @author earthlyfish
 *
 */
public class Client {

	public static void main(String[] args) {
		Component component = new ComponentImpl();
		Component decorator = new ConcretDecorator(component);
		decorator.test();
	}
}
