package test.adaptor;

/*
 * 适配器类
 */
public class AdapterOther implements Target {
	/*
	 * 持有需要被适配的接口对象
	 */
	private AdapteeOther adapteeOther;

	/*
	 * 构造方法，传入需要被适配的对象
	 * 
	 * @param adaptee 需要被适配的对象
	 */
	public AdapterOther(AdapteeOther adapteeOther) {
		this.adapteeOther = adapteeOther;
	}

	@Override
	public void request() {
		adapteeOther.otherRequest();
	}

}