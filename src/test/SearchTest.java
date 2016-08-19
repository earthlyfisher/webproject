package test;

import java.util.Arrays;
import java.util.List;

public class SearchTest {

	/**
	 * 二分查找
	 * @param numbers
	 * @param value
	 * @return
	 */
	public static boolean binarySearch(final List<Integer> numbers, final Integer value) {
		if (numbers == null || numbers.isEmpty()) {
			return false;
		}

		final Integer middle=numbers.get(numbers.size()/2);
		if(middle.equals(value)){
			return true;
		}
		
		if(middle>value){
			return binarySearch(numbers.subList(0, numbers.size()/2),value);
		}
		
		if(middle<value){
			return binarySearch(numbers.subList(numbers.size()/2,numbers.size()),value);
		}
		return false;
	}

	public static void main(String[] args) {
		Integer[] numbers = new Integer[] { 5, 6, 3, 7, 1 };
		System.out.println(binarySearch(Arrays.asList(numbers), 7));
	}
}
