package p01_bogosort;

public class Main {

	public static void main(String[] args) {
		
		Element[] arr = new Element[10];
		
		for (int i = 0; i < arr.length; i++) {
			
			arr[i] = new Element((int) (Math.random() * 100));
			System.out.print(arr[i] + " ");
		}
		
		Bogosort.sort(arr);
		
		System.out.print("\nSortirano: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}
