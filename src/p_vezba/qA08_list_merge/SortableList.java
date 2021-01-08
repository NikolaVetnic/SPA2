package p_vezba.qA08_list_merge;

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
	
	
	// MERGE SORT
	public void mergeSort() { 
		if (root != null)
			root = mergeSort(root);
	}
	
	
	private Node mergeSort(Node start) {
		
		if (start.next == null)
			return start;
		
		Node l1 = start,	  l1End = l1;
		Node l2 = start.next, l2End = l2;
		
		Node current = start.next.next;
		
		while (current != null) {
			
			l1End.next = current;
			l1End = current;
			current = current.next;
			
			if (current != null) {
				l2End.next = current;
				l2End = current;
				current = current.next;
			}
		}
		
		l1End.next = null;
		l2End.next = null;
		
		l1 = mergeSort(l1);
		l2 = mergeSort(l2);
		
		return merge(l1, l2);
	}


	private Node merge(Node l1, Node l2) {
		
		Node root = null;
		
		if (l1.info.compareTo(l2.info) < 0) {
			root = l1;
			l1 = l1.next;
		} else {
			root = l2;
			l2 = l2.next;
		}
		
		Node last = root;
		
		while (l1 != null && l2 != null) {
			
			if (l1.info.compareTo(l2.info) < 0) {
				last.next = l1;
				last = l1;
				l1 = l1.next;
			} else {
				last.next = l2;
				last = l2;
				l2 = l2.next;
			}
		}
		
		last.next = l1 == null ? l2 : l1;
		
		return root;
	}


	public static void main(String[] args) {
		
		SortableList<Integer> list = new SortableList<>();
		
		for (int i = 0; i < 10; i++) list.add(new Random().nextInt(100));
		list.print();
		
		list.mergeSort();
		list.print();
	}
}