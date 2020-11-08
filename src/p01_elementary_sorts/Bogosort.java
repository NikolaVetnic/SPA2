package p01_elementary_sorts;

public class Bogosort {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		
		while(!sorted(arr)) shuffle(arr);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean sorted(Comparable[] arr) {
		
		for (int i = 0; i < arr.length - 1; i++)
			if(arr[i].compareTo(arr[i + 1]) > 0)
				return false;
		
		return true;
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
