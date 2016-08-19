package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SortTest {

	/**
	 * 冒泡排序: 时间复杂度：o(n^2); space:o(n)
	 * 
	 * @param numbers
	 */
	public static void bubbleSort(int[] numbers) {
		/*
		 * boolean numbersSwitched; do { numbersSwitched = false; for (int i =
		 * 0; i < numbers.length - 1; i++) { if (numbers[i + 1] < numbers[i]) {
		 * int tmp = numbers[i + 1]; numbers[i + 1] = numbers[i]; numbers[i] =
		 * tmp; numbersSwitched = true; } } } while (numbersSwitched);
		 */

		/**
		 * 每次排序，能确保这次排序的最后一位是最值
		 */
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length - i - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 插入排序： 时间复杂度：o(n^2); space:o(n)
	 * 
	 * 通过借用新的列表来完成这个功能。每次挑出最值放到新列表对应位置上,通过空间复杂度来
	 * 
	 * @param numbers
	 * @return
	 */
	public static List<Integer> insertSort(final List<Integer> numbers) {
		final List<Integer> sortedList = new LinkedList<Integer>();
		/*
		 * originalList: for (Integer number : numbers) { for (int i = 0; i <
		 * sortedList.size(); i++) { if (number < sortedList.get(i)) {
		 * sortedList.add(i, number); continue originalList; } }
		 * sortedList.add(sortedList.size(), number); }
		 */

		for (Integer number : numbers) {
			// flag标签定义是否本轮循环通过比较将该位置元素添加到合适的位置
			boolean flag = true;
			for (int i = 0; i < sortedList.size(); i++) {
				if (number < sortedList.get(i)) {
					sortedList.add(i, number);
					flag = false;
					break;
				}
			}
			if (flag) {
				sortedList.add(sortedList.size(), number);
			}
		}
		return sortedList;
	}

	/**
	 * 快排：使用递归，二分法排序，最后组合的方式实现。
	 * 
	 * 时间复杂度：o(n^2); space:o(n):2n
	 * 
	 * @param numbers
	 * @return
	 */
	public static List<Integer> quicksort(List<Integer> numbers) {
		if (numbers.size() < 2) {
			return numbers;
		}
		final Integer pivot = numbers.get(0);
		final List<Integer> lower = new ArrayList<>();
		final List<Integer> higher = new ArrayList<>();
		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}
		final List<Integer> sorted = quicksort(lower);
		sorted.add(pivot);
		sorted.addAll(quicksort(higher));
		return sorted;
	}

	/**
	 * 归并排序
	 * 
	 * @param values
	 * @return
	 */
	public static List<Integer> mergesort(final List<Integer> values) {
		if (values.size() < 2) {
			return values;
		}

		final List<Integer> leftHalf = values.subList(0, values.size() / 2);
		final List<Integer> rightHalf = values.subList(values.size() / 2, values.size());
		return merge(mergesort(leftHalf), mergesort(rightHalf));
	}

	/**
	 * 以下是对两个有序集合合并重新排序
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static List<Integer> merge(final List<Integer> left, final List<Integer> right) {
		int ptrLeft = 0;
		int ptrRight = 0;
		final List<Integer> merged = new ArrayList<Integer>(left.size() + right.size());
		while (ptrLeft < left.size() && ptrRight < right.size()) {
			if (left.get(ptrLeft) < right.get(ptrRight)) {
				merged.add(left.get(ptrLeft));
				ptrLeft++;
			} else {
				merged.add(right.get(ptrRight));
				ptrRight++;
			}
		}

		while (ptrLeft < left.size()) {
			merged.add(left.get(ptrLeft));
			ptrLeft++;
		}

		while (ptrRight < right.size()) {
			merged.add(right.get(ptrRight));
			ptrRight++;
		}
		return merged;
	}

	/**
	 * 选择排序
	 * 
	 * @param values
	 * @return
	 */
	public static List<Integer> selectsort(final List<Integer> values) {

		if (values.size() < 2) {
			return values;
		}
		
		Integer[] numbers = (Integer[]) values.toArray();
		int length=numbers.length;
		for (int i = 0; i < length - 1; i++) {
			int temp = i;
			for (int j = i + 1; j <length; j++) {
				if (numbers[j] < numbers[temp]) {
					temp = j;
				}
			}
			if (temp != i) {
				//Collections.swap(values, temp, i);
				Integer middle=numbers[i];
				numbers[i]=numbers[temp];
				numbers[temp]=middle;
			}
		}
		return Arrays.asList(numbers);
	}

	public static void main(String[] args) {
		Integer[] numbers = new Integer[] { 5, 6, 3, 7, 1 };
		List<Integer> list = Arrays.asList(numbers);
		System.out.println(selectsort(list));
	}
}
