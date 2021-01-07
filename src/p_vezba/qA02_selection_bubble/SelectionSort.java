package p_vezba.qA02_selection_bubble;

import java.util.Random;

import p_vezba.qA01_bruteforce_insertion.IntWrap;

public class SelectionSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			
			for (int j = 1; j <= i; j++)
				idx = arr[j].compareTo(arr[idx]) >= 0 ? j : idx;
				
			if (idx != i) {
				Comparable tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
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
