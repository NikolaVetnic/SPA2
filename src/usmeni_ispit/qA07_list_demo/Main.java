package usmeni_ispit.qA07_list_demo;

import java.util.Random;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input list length: ");
		int n = sc.nextInt();
		
		SortableList<Integer> l = new SortableList<Integer>();
		populate(l, n);
		System.out.println("[ Original list ]\t" + l.print() + "\n");
		
		sortMenu(l);
		System.out.println("[ Sorted list ]\t\t" + l.print() + "\n");
		
		sc.close();
	}
	
	
	public static void populate(SortableList<Integer> l, int n) {
			
		for (int i = 0; i < n; i++)
			l.add(new Random().nextInt(100));
		
		System.out.println();
	}
	
	
	public static <T extends Comparable<T>> void sortMenu(SortableList<T> l) {
		
		System.out.println("Select sort algorithm:\n" +
						   "[0] Insertion Sort	  \n" +
						   "[1] Merge Sort		  \n" +
						   "[2] Quick Sort	  	  ");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input: ");
		int sel = sc.nextInt();
		
		switch (sel) {
			case 0:	l.insertionSort();						break;
			case 1:	l.mergeSort();							break;
			case 2: l.quickSort();							break;
			default: System.out.println("End of program.");	break;
		}
		
		System.out.println();
		
		sc.close();
	}
}
