package p01_insertion;

public class InsertionSort {
	
	public static void insertionSort_v1(Comparable[] arr) {
		
		// podniz od jednog elementa je sortiran
		for (int i = 1; i < arr.length; i++) {
			
			// ako je prvi nesortiran manji od poslednjeg sortiranog
			if (arr[i].compareTo(arr[i-1]) < 0) {
				
				Comparable current = arr[i];
				
				// trazi se j tako da je arr[j] <= current
				int j = i - 1;
				while (j >= 0 && arr[j].compareTo(current) > 0) {
					
					arr[j+1] = arr[j];
					j--;
				}
				
				arr[j+1] = current;
			}
		}
	}
	
	
	public static void insertionSort_v2(Comparable arr[]) {
		
		for (int i = 1; i < arr.length; i++) {
			
			// prva provera iz v1 se moze izostaviti
			Comparable current = arr[i];
			
			int j = i - 1;
			while (j >= 0 && arr[j].compareTo(current) > 0) {
				
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	/**
	 * U slučaju kada su svi elementi sortiranog dela niza veći od prv-
	 * og nesortiranog elementa while petlja će se završiti kada j pos-
	 * tane -1, što može predstavljati problem u jezicima koji ne podr-
	 * žavaju lenjo izračunavanje logičkih izraza; ono što se dešava z-
	 * apravo je da imamo sledeću proveru:
	 * 		arr[-1].compareTo...
	 * 
	 * Ne postoji element niza sa indeksom -1.
	 * 
	 * POJAŠNJENJE: ukoliko nije podržano lenjo izračunavanje OBA uslo-
	 * va konjukcije (j >= 0 && arr[j].compareTo(current) > 0) bi se p-
	 * roveravala čak i ako je već prvi false, što dovodi do greške bu-
	 * dući da ne postoji element sa indeksom -1.
	 */
	 
	 public static void insertionSort_v3(Comparable[] arr) {
		 
		int minInd = 0;
		for (int i = 1; i < arr.length; i++)
			if (arr[i].compareTo(arr[minInd]) < 0)
				minInd = i;
				
		if (minInd != 0) {
			
			Comparable tmp = arr[minInd];
			arr[minInd] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Comparable current = arr[i];
			
			int j = i - 1;
			while (arr[j].compareTo(current) > 0) {
				
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
}
