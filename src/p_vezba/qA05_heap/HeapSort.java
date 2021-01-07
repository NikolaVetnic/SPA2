package p_vezba.qA05_heap;

import java.util.Random;

import p_vezba.qA01_bruteforce_insertion.IntWrap;

public class HeapSort {

	public static <T extends Comparable<T>> void sort(T[] arr) {
		
		int lastIndex = arr.length - 1;
		int lastParent = (lastIndex - 1) / 2;
		
		while (lastParent >= 0) {
			makeHeap(arr, lastParent, lastIndex);
			lastParent--;
		}
		
		int end = lastIndex;
		
		while (end > 0) {
			
			T tmp = arr[0];
			arr[0] = arr[end];
			arr[end] = tmp;
			
			end--;
			
			makeHeap(arr, 0, end);
		}
	}

	private static <T extends Comparable<T>> void makeHeap(T[] arr, int start, int end) {
		
		int parentIndex = start;
		boolean heapRestored = false;
		
		while (!heapRestored) {
			
			int maxSonIndex = getMaxSon(arr, parentIndex, end);
			
			if (maxSonIndex == -1) {
				heapRestored = true;
			} else {
				if (arr[parentIndex].compareTo(arr[maxSonIndex]) < 0) {
					T tmp = arr[parentIndex];
					arr[parentIndex] = arr[maxSonIndex];
					arr[maxSonIndex] = tmp;
					
					parentIndex = maxSonIndex;
				} else {
					heapRestored = true;
				}
			}
		}
	}

	private static <T extends Comparable<T>> int getMaxSon(T[] arr, int parentIndex, int end) {
		
		int son1Index = 2 * parentIndex + 1;
		int son2Index = 2 * parentIndex + 2;
		int maxSonIndex = -1;
		
		if (son1Index <= end)
			maxSonIndex = son1Index;
		
		if (son2Index <= end)
			if (arr[son2Index].compareTo(arr[maxSonIndex]) > 0)
				maxSonIndex = son2Index;
		
		return maxSonIndex;
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
