package p03_advanced_sorts;

public class QuickSortMidArr {
	
	/*
	 * Quick sort metod u kojem se primenjuje sema particionisanja niza
	 * sa pivotom u sredini.
	 */

	public static <T extends Comparable<T>> void sort(T[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<T>> void sort(T[] arr, int l, int h) {
		T pivot = arr[(l + h) / 2];
		int i = l;
		int j = h;
		
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
