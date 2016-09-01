package test.adaptor;

/*
 * 已经存在的接口，这个接口需要配置
 */
public class AdapteeOther {
	/*
	 * 原本存在的方法
	 */
	public void otherRequest() {
		// 业务代码
		System.out.println("我是另一个目标");
	}
}