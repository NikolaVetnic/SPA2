package usmeni_ispit.qA09_list_quick;

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
	
	
	// QUICK SORT
	public void quickSort() { 
		if (root != null)
			root = quickSort(root);
	}


	private Node quickSort(Node start) {
		
		if (start.next == null)
			return start;
		
		Node pivot = start;
		
		Node smaller = null;
		Node greater = null;
		
		Node current = pivot.next;
		
		while (current != null) {
			
			if (current.info.compareTo(pivot.info) < 0) {
				Node tmp = current.next;
				current.next = smaller;
				smaller = current;
				
				current = tmp;
			} else {
				Node tmp = current.next;
				current.next = greater;
				greater = current;
				
				current = tmp;
			}
		}
		
		if (smaller != null) smaller = quickSort(smaller);
		if (greater != null) greater = quickSort(greater);
		
		pivot.next = greater;
		
		if (smaller == null) {
			return pivot;
		} else {
			Node tmp = smaller;
			while (tmp.next != null) tmp = tmp.next;
			tmp.next = pivot;
			return smaller;
		}
	}


	public static void main(String[] args) {
		
		SortableList<Integer> list = new SortableList<>();
		
		for (int i = 0; i < 10; i++) list.add(new Random().nextInt(100));
		list.print();
		
		list.quickSort();
		list.print();
	}
}