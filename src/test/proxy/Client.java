package test.proxy;

/**
 * 动态代理： 
 * 1.采用JDK动态代理必须实现InvocationHandler接口，采用Proxy类创建相应的代理类
 * 2.JDK动态代理只能对实现了接口的类进行代理
 * 3.可以解决创建多个静态代理的麻烦，避免不断的重复多余的代码
 * 4.调用目标代码时，会在方法“运行时”动态的加入，决定你是什么类型，才调谁，灵活
 * 5.系统灵活了，但是相比而言，效率降低了，比静态代理慢一点
 * 6.代理类起中介的作用，具体的动作还得具体的委托类实现
 * 
 * @author earthlyfish
 *
 */
public class Client {

	public static void main(String[] args) {
		ProxyHandler proxyHandler = new ProxyHandler();
		Component component = (Component) proxyHandler.newProxyInstance(new ComponentImpl());
        component.test();
	}
}
