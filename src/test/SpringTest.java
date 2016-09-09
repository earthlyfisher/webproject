package test;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringTest {

	@Test
	public void spelTest(){
		ExpressionParser parser = new SpelExpressionParser();
		// invokes 'getBytes().length'
		Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		int length = (Integer) exp.getValue();
		System.out.println(length);
	}
	
}
