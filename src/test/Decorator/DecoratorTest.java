package test.Decorator;

public class DecoratorTest implements Component{

	private Component component;

	
	public DecoratorTest(Component component) {
		super();
		this.component = component;
	}


	@Override
	public void test() {
      component.test();		
	}
	
	
}
