package usmeni_ispit.qA01_elementary_sorts;

public class BubbleSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort_v1(Comparable[] arr) {	
		for (int i = arr.length - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (arr[j].compareTo(arr[j+1]) > 0) {
					Comparable tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort_v2(Comparable[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			for (int j = arr.length - 1; j > i; j--)
				if (arr[j].compareTo(arr[j-1]) < 0) {
					Comparable tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
	}
	
	/**
	 * Bubble sort se može prekinuti nakon iteracije u kojoj se nije d-
	 * esila nijedna razmena elemenata, što je pristup primenjiv na obe
	 * varijante sortiranja.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort_v3(Comparable[] arr) {	
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exchOccured = false;
			
			for (int j = 0; j < i; j++)
				if (arr[j].compareTo(arr[j+1]) > 0) {
					Comparable tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exchOccured = true;
				}
			
			if (!exchOccured) break;
		}
	}
}
