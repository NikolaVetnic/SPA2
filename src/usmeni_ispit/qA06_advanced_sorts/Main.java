package usmeni_ispit.qA06_advanced_sorts;

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
						   "[0] Heap Sort		  \n" +
						   "[1] Quick Sort (H)	  \n" +
						   "[2] Quick Sort (L)	  \n" +
						   "[3] Quick Sort (M)	  ");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input: ");
		int sel = sc.nextInt();
		
		switch (sel) {
			case 0:	HeapSort.sort(arr);						break;
			case 1:	QuickSortHoare.sort(arr);				break;
			case 2: QuickSortLomuto.sort(arr);				break;
			case 3: QuickSortMidArr.sort(arr);				break;
			default: System.out.println("End of program.");	break;
		}
		
		System.out.println();
		
		sc.close();
	}
}
