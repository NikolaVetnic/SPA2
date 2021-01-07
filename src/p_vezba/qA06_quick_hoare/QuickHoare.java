package p_vezba.qA06_quick_hoare;

import java.util.Random;

import p_vezba.qA01_bruteforce_insertion.IntWrap;

public class QuickHoare {

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
		
		T pivot = arr[l];
		int i = l + 1;
		int j = h;
		
		while (i <= j) {
			
			while (i <= h && arr[i].compareTo(pivot) < 0) i++;
			while (j >= l + 1 && arr[j].compareTo(pivot) > 0) j--;
			
			if (i <= j) {
				T tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		
		T tmp = arr[j];
		arr[j] = arr[l];
		arr[l] = tmp;
		
		return j;
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
