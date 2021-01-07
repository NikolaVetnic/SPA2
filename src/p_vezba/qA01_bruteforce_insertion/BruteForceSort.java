package p_vezba.qA01_bruteforce_insertion;

import java.util.Random;

public class BruteForceSort {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int j = 1; j < arr.length; j++) {
			for (int i = 0; i < j; i++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					Comparable tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		IntWrap[] arr = new IntWrap[10];
		for (int i = 0; i < 10; i++)
			System.out.print((arr[i] = new IntWrap(new Random().nextInt(100))) + " ");
		
		System.out.println();
		sort(arr);
		
		for (int i = 0; i < 10; i++)
			System.out.print(arr[i] + " ");
	}
}
