package p_vezba.qA03_shell;

import java.util.Random;

import p_vezba.qA01_bruteforce_insertion.IntWrap;

public class ShellSort {

	static int[] gaps = { 4, 2, 1 };
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] arr) {
		
		for (int g : gaps) {
			for (int i = 0; i < g; i++) {
				for (int j = i + g; j < arr.length; j += g) {
					
					Comparable curr = arr[j];
					int prevIdx = j - g;
					
					while (prevIdx >= 0 && arr[prevIdx].compareTo(curr) > 0) {
						arr[prevIdx + g] = arr[prevIdx];
						prevIdx -= g;
					}
					
					arr[prevIdx + g] = curr;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		IntWrap[] arr = new IntWrap[10];
		for (int i = 0; i < 10; i++)
			System.out.print((arr[i] = new IntWrap(new Random().nextInt(100))) + " ");
		
		System.out.println();
		sort(arr);
		
		for (int i = 0; i < 10; i++)
			System.out.print(arr[i] + " ");
	}
}
