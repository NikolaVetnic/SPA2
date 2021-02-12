package prakticne_vezbe.pv01_knjige_p02;

import java.util.Comparator;

public class Sort {
	
	public void bubble(Knjiga[] arr, Comparator<Knjiga> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					Knjiga tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch)
				break;
		}
	}
	
	public void insertion(Knjiga[] arr, Comparator<Knjiga> c) {
		
		int idx = 0;
		for (int i = 1; i < arr.length; i++)
			idx = c.compare(arr[idx], arr[i]) < 0 ? idx : i;
		
		if (idx != 0) {
			Knjiga tmp = arr[0];
			arr[0] = arr[idx];
			arr[idx] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Knjiga current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	public void selection(Knjiga[] arr, Comparator<Knjiga> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 1; j <= i; j++)
				idx = c.compare(arr[j], arr[idx]) > 0 ? j : idx;
			
			if (idx != i) {
				Knjiga tmp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = tmp;
			}
		}
	}
}
