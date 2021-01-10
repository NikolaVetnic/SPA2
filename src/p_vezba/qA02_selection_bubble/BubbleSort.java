package p_vezba.qA02_selection_bubble;

import java.util.Random;

public class BubbleSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exchOccurred = false;
			
			for (int j = 0; j < i; j++)
				if (arr[j].compareTo(arr[j + 1]) >= 0) {
					Comparable tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					
					exchOccurred = true;
				}
			
			if (!exchOccurred) break;
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
