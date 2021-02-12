package kolokvijumi.kol1_v_z01_p02;

import java.util.Comparator;

public class Sort {

	public static void bubble(Predmet[] arr, Comparator<Predmet> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					Predmet tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch)
				break;
		}
	}
	
	public static void insertion(Predmet[] arr, Comparator<Predmet> c) {
		
		int idx = 0;
		for (int i = 1; i < arr.length; i++)
			idx = c.compare(arr[i], arr[idx]) < 0 ? i : idx;
		
		if (idx != 0) {
			Predmet tmp = arr[0];
			arr[0] = arr[idx];
			arr[idx] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Predmet current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	public static void selection(Predmet[] arr, Comparator<Predmet> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 0; j <= i; j++)
				idx = c.compare(arr[idx], arr[j]) > 0 ? idx : j;
				
			if (idx != i) {
				Predmet tmp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = tmp;
			}
		}
	}
}
