package p01_bruteforce;

import java.util.Comparator;

public class BruteForceSort {
	
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
	
	
	public static void sort(Object[] arr, Comparator cmp) {
		
		for (int j = 1; j < arr.length; j++) {
			for (int i = 0; i < j; i++) {
				
				if (cmp.compare(arr[i], arr[j]) > 0) {
					
					Object tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}