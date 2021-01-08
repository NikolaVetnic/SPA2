package p_vezba.qA07_list_insertion;

import java.util.Random;

public class SortableList<T extends Comparable<T>> {

	
	private class Node {
		
		T info;
		Node next;
		
		public Node(T info) { 
			this.info = info; 
		}
	}
	
	
	private Node root = null;
	
	
	public void add(T info) {
		Node newElement = new Node(info);
		newElement.next = root;
		root = newElement;
	}

	
	public String print() {
		
		if (root != null) {
			
			String out = "";
			
			Node curr = root;
			while (curr.next != null) {
				out += curr.info + " ";
				curr = curr.next;
			}
			
			System.out.println(out);
			return out;
		}
		
		return null;
	}
	
	
	// INSERTION SORT
	public void insertionSort() { 
		
		if (root == null || root.next == null)
			return;
		
		Node lastSorted = root;
		
		while (lastSorted.next != null) {
			
			Node firstUnsorted = lastSorted.next;
			
			if (firstUnsorted.info.compareTo(lastSorted.info) >= 0) {
				lastSorted = firstUnsorted;
			} else {
				if (firstUnsorted.info.compareTo(root.info) < 0) {
				
					lastSorted.next = firstUnsorted.next;
					firstUnsorted.next = root;
					root = firstUnsorted;
				} else {
					
					Node prev = null, curr = root;
					
					while (curr.info.compareTo(firstUnsorted.info) < 0) {
						prev = curr;
						curr = curr.next;
					}
					
					lastSorted.next = firstUnsorted.next;
					firstUnsorted.next = prev.next;
					prev.next = firstUnsorted;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		SortableList<Integer> list = new SortableList<>();
		
		for (int i = 0; i < 10; i++) list.add(new Random().nextInt(100));
		list.print();
		
		list.insertionSort();
		list.print();
	}
}