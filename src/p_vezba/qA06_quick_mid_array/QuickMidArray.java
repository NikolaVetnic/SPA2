package p_vezba.qA06_quick_mid_array;

public class QuickMidArray {

	
	static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	

	private static void sort(Comparable[] arr, int l, int h) {
		
		int i = l,
			j = h,
			pivot = (l + h) / 2;
		
		while (i <= j) {
			
			while (arr[i].compareTo(arr[pivot]) < 0) i++;
			while (arr[j].compareTo(arr[pivot]) > 0) j++;
			
			if (i <= j) {
				Comparable t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
				i++;
				j--;
			}
		}
		
		if (l < j) sort(arr, l, j);
		if (i < h) sort(arr, i, h);
	}
}
