package usmeni_ispit.qA06_advanced_sorts;

public class QuickSortMidArr {

	static <T extends Comparable<T>> void sort(T[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable<T>> void sort(T[] arr, int l, int h) {
		
		T pivot = arr[(arr.length - 1) / 2];
		int i = l, j = h;
		
		while (i <= j) {
			
			while (arr[i].compareTo(pivot) < 0) i++;
			while (arr[j].compareTo(pivot) > 0) j--;
			
			if (i <= j) {
				T tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		
		if (l < j) sort(arr, l, j);
		if (i < h) sort(arr, i, h);
	}
}
