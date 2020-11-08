package p01_elementary_sorts;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input array length: ");
		int n = sc.nextInt();
		
		Element[] arr = arr(n);
		print("Original array", arr);
		System.out.println();
		
		sortMenu(arr);
		print("Sorted array", arr);
		
		sc.close();
	}
	
	public static void print(String label, Element[] arr) {
		
		System.out.printf("%-20s", ("[ " + label + " ]"));
		
		for (int i = 0; i < arr.length; i++)
			System.out.printf("%d ", arr[i].info());
		
		System.out.println();
	}
	
	public static Element[] arr(int n) {
		
		Element[] arr = new Element[n];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = new Element((int) (Math.random() * 100));
		
		System.out.println();
		
		return arr;
	}
	
	public static void sortMenu(Element[] arr) {
		
		System.out.println("Select sort algorithm:\n" +
						   "[0] Bogosort		  \n" +
						   "[1] Brute Force		  \n" +
						   "[2] Insertion Sort	  \n" +
						   "[3] Selection Sort	  \n" +
						   "[4] Bubble Sort		  \n" +
						   "[5] Shell Sort		  \n" +
						   "[6] Comb Sort			");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input: ");
		int sel = sc.nextInt();
		
		switch (sel) {
			case 0:	Bogosort.sort(arr);						break;
			case 1:	BruteForce.sort(arr);					break;
			case 2: InsertionSort.insertionSort_v3(arr);	break;
			case 3: SelectionSort.sort_v2(arr);				break;
			case 4: BubbleSort.sort_v3(arr); 				break;
			case 5: ShellSort.sort(arr); 					break;
			case 6: CombSort.sort(arr);						break;
			default: System.out.println("End of program.");	break;
		}
		
		System.out.println();
		
		sc.close();
	}
}
