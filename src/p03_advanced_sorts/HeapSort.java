package p03_advanced_sorts;

public class HeapSort {

	public static <T extends Comparable<T>> void sort(T[] arr) {
		
		int lastIndex = arr.length - 1;
		int lastParent = (lastIndex - 1) / 2;	// poslednji otac
		
		// uspostavi strukturu heap-a
		while (lastParent >= 0) {
			makeHeap(arr, lastParent, lastIndex);
			lastParent--;
		}
		
		// indeks poslednjeg elementa u nesortiranom delu niza
		int end = lastIndex;
		
		while (end > 0) {
			
			// Razmeni prvi element sa poslednjim elementom iz nesortiranog de-
			// la niza.
			T tmp = arr[0];
			arr[0] = arr[end];
			arr[end] = tmp;
			
			// Nesortirani deo niza je sada kraci za jedan element.
			end--;
			
			// Uspostavi strukturu heap-a u nesortiranom delu.
			makeHeap(arr, 0, end);
		}
	}

	private static <T extends Comparable<T>> 
	void makeHeap(T[] arr, int start, int end) {
		
		int parentIndex = start;
		boolean heapRestored = false;
		
		while (!heapRestored) {
			
			int maxSonIndex = getMaxSon(arr, parentIndex, end);
			
			// ne postoji nijedan od sinova
			if (maxSonIndex == -1) {
				
				heapRestored = true;
			} else {
				
				// uporedi oca sa vecim sinom
				if (arr[parentIndex].compareTo(arr[maxSonIndex]) < 0) {
					
					// razmeni oca sa vecim sinom
					T tmp = arr[maxSonIndex];
					arr[maxSonIndex] = arr[parentIndex];
					arr[parentIndex] = tmp;
					
					// otac je sada na poziciji veceg sina
					parentIndex = maxSonIndex;
				} else {
					
					heapRestored = true;
				}
			}
		}
	}
	
	/**
	 * Odredjuje indeks veceg sina za datog roditelja; vraca -1 ukoliko
	 * ne postoji nijedan od sinova.
	 */
	private static <T extends Comparable<T>> int getMaxSon(T[] arr, int parentIndex, int end) {
		
		int son1Index = 2 * parentIndex + 1;
		int son2Index = 2 * parentIndex + 2;
		int maxSonIndex = -1;
		
		if (son1Index <= end)
			maxSonIndex = son1Index;
		
		if (son2Index <= end)
			if (arr[son2Index].compareTo(arr[son1Index]) > 0)
				maxSonIndex = son2Index;
		
		return maxSonIndex;
	}
}
