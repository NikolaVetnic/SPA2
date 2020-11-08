package p01_elementary_sorts;

public class SelectionSort {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void sort_v1(Comparable[] arr) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			
			for (int j = 0; j <= i; j++)
				idx = arr[j].compareTo(arr[idx]) >= 0 ? j : idx;
			
			if (idx != i) {
				Comparable tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void sort_v2(Comparable[] arr) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			
			int idx = i;
			
			for (int j = i + 1; j < arr.length; j++)
				idx = arr[j].compareTo(arr[idx]) < 0 ? j : idx;
			
			if (idx != i) {
				Comparable tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
			}
		}
	}
}
