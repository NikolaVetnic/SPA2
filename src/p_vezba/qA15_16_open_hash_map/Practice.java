package p_vezba.qA15_16_open_hash_map;

public class Practice<T extends Comparable<T>> {
	
	
	static int[] gaps = { 4, 2, 1 };
	
	
	static void sort(Comparable[] arr) {
		
		for (int g : gaps) {
			for (int i = 0; i < g; i++) {
				for (int j = i + g; j < arr.length; j += g) {
					Comparable t = arr[j];
					int k = j - g;
					
					while (k >= 0 && t.compareTo(arr[k]) < 0) {
						arr[k + g] = arr[k];
						k -= g;
					}
					
					arr[k + g] = t;
				}
			}
		}
	}
}
