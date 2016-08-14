package test.Decorator;

public class ConcretDecorator extends DecoratorTest {

	public ConcretDecorator(Component component) {
		super(component);
	}

	@Override
	public void test() {
		super.test();
		this.doSomething();
	}
	
	private void doSomething(){
		System.out.println("我是具体的装饰类");
	}
}
