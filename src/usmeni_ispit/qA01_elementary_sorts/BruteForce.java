package usmeni_ispit.qA01_elementary_sorts;

public class BruteForce {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int j = 1; j < arr.length; j++) {
			for (int i = 0; i < i; i++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					Comparable tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}
