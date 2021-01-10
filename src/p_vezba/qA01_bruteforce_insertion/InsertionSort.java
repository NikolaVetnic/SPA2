package p_vezba.qA01_bruteforce_insertion;

import java.util.Random;

public class InsertionSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int i = 1; i < arr.length; i++) {
			
			Comparable curr = arr[i];
			int j = i - 1;
			
			while (j >= 0 && arr[j].compareTo(curr) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			
			arr[j + 1] = curr;
		}
	}
	
	public static void main(String[] args) {
		
		Element[] arr = new Element[10];
		for (int i = 0; i < 10; i++)
			System.out.print((arr[i] = new Element(new Random().nextInt(100))) + " ");
		
		System.out.println();
		sort(arr);
		
		for (int i = 0; i < 10; i++)
			System.out.print(arr[i] + " ");
	}
}
