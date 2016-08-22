package test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

	private Object targetObject;

	public Object newProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 记录日志等操作或打印输入参数
		System.out.println("start-->>" + method.getName());
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				// 打印调用目标方法的参数
				System.out.println(args[i]);
			}
		}
		Object ret = null;
		try {
			// 调用目标方法
			ret = method.invoke(targetObject, args);
			// 执行成功，打印成功信息
			System.out.println("success-->>" + method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			// 失败时，打印失败信息
			System.out.println("error-->>" + method.getName());
			throw e;
		}

		return ret;
	}

}
