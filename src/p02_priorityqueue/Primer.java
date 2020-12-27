package p02_priorityqueue;

import java.util.Random;

public class Primer {
	
	
	public static class Element implements Comparable<Element> {

		public final int info;
		
		public Element(int info) 		{ this.info = info; 	}
		public String toString() 		{ return "" + info; 	}
		
		@Override
		public int compareTo(Element e) { return info - e.info; }
	}
	
	
	public static void printArr(Element[] arr) {
		
		System.out.print("Arr [ ");
		
		for (Element e : arr)
			System.out.print(e + " ");
		
		System.out.println("]");
	}

	
	public static void main(String[] args) {
		
		int numEl = 10;
		Element[] arr = new Element[numEl];
		
		for (int i = 0; i < numEl; i++)
			arr[i] = new Element(new Random().nextInt(100));
		
		printArr(arr);
		
		HeapPQ<Element> pq = new HeapPQ<>(numEl);
		for (Element e : arr)
			pq.insert(e);
		
		int count = 0;
		Element[] sortedArr = new Element[numEl];
		
		while (!pq.isEmpty())
			sortedArr[count++] = pq.delMax();
		
		printArr(sortedArr);
	}
}