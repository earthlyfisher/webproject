package test;

public class StringTest {

	private static void internTest() {
		String s1 = "123";
		String s2 = new String("123");
		System.out.println("equals:" + (s1.equals(s2)) + " , ==:" + (s1 == s2));
		s2 = s2.intern();
		System.out.println("equals:" + (s1.equals(s2)) + " , ==:" + (s1 == s2));
	}

	public static void main(String[] args) {
		// intern method test
		internTest();
	}
}
