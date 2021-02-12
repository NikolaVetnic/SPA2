package kolokvijumi.kol1_grupa4;

import java.util.Comparator;

public class Sort {

	public static void bubble(KursnaLista[] arr, Comparator<KursnaLista> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					KursnaLista tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch) break;
		}
	}
	
	public static void insertion(KursnaLista[] arr, Comparator<KursnaLista> c) {
		
		int idx = 0;
		for (int i = 1; i < arr.length; i++)
			idx = c.compare(arr[i], arr[idx]) < 0 ? i : idx;
		
		if (idx != 0) {
			KursnaLista tmp = arr[idx];
			arr[idx] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			KursnaLista current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	public static void selection(KursnaLista[] arr, Comparator<KursnaLista> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 0; j <= i; j++)
				idx = c.compare(arr[j], arr[idx]) > 0 ? j : idx;
				
			if (idx != i) {
				KursnaLista tmp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = tmp;
			}
		}
	}
}
