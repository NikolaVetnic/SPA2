package pv02_sort_p02;

import java.util.Comparator;

public class Sort {

	public static void bubble(Student[] arr, Comparator<Student> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			boolean exch = false;
			
			for (int j = 0; j < i; j++)
				if (c.compare(arr[j], arr[j+1]) > 0) {
					Student tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					exch = true;
				}
			
			if (!exch)
				break;
		}
	}
	
	public static void insertion(Student[] arr, Comparator<Student> c) {
		
		int idx = 0;
		for (int i = 0; i < arr.length; i++)
			idx = c.compare(arr[idx], arr[i]) < 0 ? idx : i;
		
		if (idx != 0) {
			Student tmp = arr[idx];
			arr[idx] = arr[0];
			arr[0] = tmp;
		}
		
		for (int i = 2; i < arr.length; i++) {
			
			Student current = arr[i];
			int j = i - 1;
			
			while (c.compare(arr[j], current) > 0) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
	
	public static void selection(Student[] arr, Comparator<Student> c) {
		
		for (int i = arr.length - 1; i > 0; i--) {
			
			int idx = 0;
			for (int j = 1; j <= i; j++)
				idx = c.compare(arr[idx], arr[j]) > 0 ? idx : j;
			
			if (idx != i) {
				Student tmp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = tmp;
			}
		}
	}
}
