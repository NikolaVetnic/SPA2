package p03_advanced_sorts;

public class QuickSortLomuto {
	
	/*
	 * Quick sort metod u kojem se primenjuje Lomutoova sema particion-
	 * isanja niza.
	 */

	public static <T extends Comparable<T>> void sort(T[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable<T>> void sort(T[] arr, int l, int h) {
		
		if (l < h) {
			int j = partition(arr, l, h);
			sort(arr, l, j - 1);
			sort(arr, j + 1, h);
		}
	}

	private static <T extends Comparable<T>> int partition(T[] arr, int l, int h) {
		
		T pivot = arr[h];
		int ltePivot = l - 1;
		
		for (int i = l; i < h; i++) {
			if (arr[i].compareTo(pivot) <= 0) {
				++ltePivot;
				T tmp = arr[ltePivot];
				arr[ltePivot] = arr[i];
				arr[i] = tmp;
			}
		}
		
		int placeForPivot = ltePivot + 1;
		
		T tmp = arr[placeForPivot];
		arr[placeForPivot] = arr[h];
		arr[h] = tmp;
		
		return placeForPivot;
	}
}
