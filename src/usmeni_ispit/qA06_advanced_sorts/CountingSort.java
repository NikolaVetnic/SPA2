package usmeni_ispit.qA06_advanced_sorts;

public class CountingSort {

	public static void sort(int[] arr) {
		
		// pronalazimo maksimum niza
		int max = arr[0];
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > max) max = arr[i];
		
		// formiramo distribuciju
		int[] distr = new int[max + 1];
		for (int i = 0; i < arr.length; i++)
			distr[arr[i]]++;
		
		// na osnovu distribucije formiramo niz
		int currentPosition = 0;
		for (int i = 0; i < distr.length; i++)
			for (int j = 0; j < distr[i]; j++)
				arr[currentPosition++] = i;
	}
}
