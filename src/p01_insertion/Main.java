package p01_insertion;

public class Main {

	public static void main(String[] args) {
		
		Element[] arr = new Element[10];
		
		System.out.print("Nesortirano: ");
		for (int i = 0; i < arr.length; i++) {
			
			arr[i] = new Element((int) (Math.random() * 100));
			System.out.print(arr[i] + " ");
		}
		
		InsertionSort.insertionSort_v1(arr);
		
		System.out.print("\nInsertionSort, v1: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		
		InsertionSort.insertionSort_v2(arr);
		
		System.out.print("\nInsertionSort, v2: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		
		InsertionSort.insertionSort_v3(arr);
		
		System.out.print("\nInsertionSort, v3: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}
