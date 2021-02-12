package usmeni_ispit.qA04_comb;

import java.util.Random;

public class CombSort {

	private static int nextGap(int k) {
		
		k /= 1.3;
		
		if (k == 9 || k == 10)	k = 11;
		else if (k < 1)			k = 1;
		
		return k;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		boolean sorted = false;
		int k = arr.length;
		
		do {
			k = nextGap(k);
			boolean exchOccurred = false;
			
			for (int i = 0; i + k < arr.length; i++)
				if (arr[i].compareTo(arr[i + k]) > 0) {
					Comparable tmp = arr[i];
					arr[i] = arr[i + k];
					arr[i + k] = tmp;
					
					exchOccurred = true;
				}
			
			sorted = k == 1 || !exchOccurred;
			
		} while (!sorted);
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
