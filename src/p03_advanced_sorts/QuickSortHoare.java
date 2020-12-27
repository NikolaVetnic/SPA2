package p03_advanced_sorts;

public class QuickSortHoare {
	
	/*
	 * Quick sort metod u kojem se primenjuje Hoareova sema particioni-
	 * sanja niza.
	 */ 

	public static <T extends Comparable<T>> void sort(T[] arr) {
		
		/*
		 * Performanse Quick Sort-a drasticno opadaju ako niz ima veli-
		 * ki stepen sortiranosti.
		 */
		shuffle(arr);
		
		sort(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable<T>> void sort(T[] arr, int l, int h) {
		if (l < h) {
			
			/*
			 * Podnizove male duzine (h - l < 17) je efikasnije sortir-
			 * ati elementarnim metodama.
			 */
			if (h - l < 17) 
				InsertionSort.insertionSort_v3(arr);
			
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
		
		T tmp = arr[l];
		arr[l] = arr[j];
		arr[j] = tmp;
		
		return j;
	}
	
	@SuppressWarnings("rawtypes")
	private static void shuffle(Comparable[] arr) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int rndIndex = (int) (Math.random() * i);
			
			Comparable tmp = arr[i];
			arr[i] = arr[rndIndex];
			arr[rndIndex] = tmp;
		}
	}
}
