package usmeni_ispit.qA01_elementary_sorts;

public class ShellSort {

	private static int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
	
	public static <T extends Comparable<T>> void sort(T[] arr) {
		
		for (int k : gaps) {
			
			if (k > arr.length) continue;
			
			for (int i = 0; i < k; i++) {
				for (int j = i + k; j < arr.length; j += k) {
					
					T current = arr[j];
					int prevIndex = j - k;
					
					while (prevIndex >= i && arr[prevIndex].compareTo(current) > 0) {
						arr[prevIndex + k] = arr[prevIndex];
						prevIndex -= k;
					}
					
					arr[prevIndex + k] = current;
				}
			}
		}
	}
}
