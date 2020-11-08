package p01_elementary_sorts;

public class CombSort {

	private static int nextGap(int k) {
		k /= 1.3;
		
		if (k == 9 || k == 10) 	k = 11;
		else if (k < 1) 		k = 1 ;
		
		return k;
	}
	
	public static <T extends Comparable<T>> void sort(T[] arr) {
		
		boolean sorted = false;
		int k = arr.length;
		
		do {
			k = nextGap(k);
			boolean exchOccured = false;
			
			for (int i = 0; i < arr.length - k; i++) {
				if (arr[i].compareTo(arr[i+k]) > 0) {
					
					T tmp = arr[i];
					arr[i] = arr[i+k];
					arr[i+k] = tmp;
					
					exchOccured = true;
				}
			}
			
			sorted = k == 1 && !exchOccured;
		} while(!sorted);
	}
}
