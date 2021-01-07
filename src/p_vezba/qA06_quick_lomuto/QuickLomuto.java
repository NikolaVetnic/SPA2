package p_vezba.qA06_quick_lomuto;

import java.util.Random;

public class QuickLomuto {

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
				T tmp = arr[i];
				arr[i] = arr[ltePivot];
				arr[ltePivot] = tmp;
			}
		}
		
		int placeForPivot = ltePivot + 1;
		
		T tmp = arr[placeForPivot];
		arr[placeForPivot] = arr[h];
		arr[h] = tmp;
		
		return placeForPivot;
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
