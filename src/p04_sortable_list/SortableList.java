package p04_sortable_list;

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
			
			// firstUnsorted >= lastSorted -- nema umetanja
			if (firstUnsorted.info.compareTo(lastSorted.info) >= 0) {
				lastSorted = firstUnsorted;
			} else {
				if (firstUnsorted.info.compareTo(root.info) < 0) {
					// firstUnsorted < root
					lastSorted.next = firstUnsorted.next;
					firstUnsorted.next = root;
					root = firstUnsorted;
				} else {
					// pronalazenje lastLeq
					Node current = root, lastLeq = null;
					
					while (current.info.compareTo(firstUnsorted.info) <= 0) {
						lastLeq = current;
						current = current.next;
					}
					
					// umetanje firstUnsorted iza lastLeq
					lastSorted.next = firstUnsorted.next;
					firstUnsorted.next = lastLeq.next;
					lastLeq.next = firstUnsorted;
				}
			}
		}
	}
	
	
	// MERGE SORT
	public void mergeSort() { 
		if (root != null)
			root = mergeSort(root);
	}
	
	private Node mergeSort(Node start) {
		
		// jednoelementna lista je sortirana
		if (start.next == null)
			return start;
		
		// podela liste na dva dela l1 i l2
		Node l1 = start, l1End = l1;
		Node l2 = start.next, l2End = l2;
		
		Node current = start.next.next;
		while (current != null) {
			// dodaj current u l1
			l1End.next = current;
			l1End = current;
			
			// pomeri current za jedno mesto udesno
			current = current.next;
			
			// dodaj current u l2
			if (current != null) {
				l2End.next = current;
				l2End = current;
				current = current.next;
			}
		}
		
		// postavi uzemljenja
		l1End.next = null;
		l2End.next = null;
		
		l1 = mergeSort(l1);
		l2 = mergeSort(l2);
		
		// spajanje sortiranih listi
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

	
	// QUICK SORT
	public void quickSort() { 
		if (root != null)
			root = quickSort(root);
	}
	
	private Node quickSort(Node start) {
		
		// lista koja ima jedan element je sortirana
		if (start.next == null)
			return start;
		
		// za pivot selektujemo prvi element liste start
		Node pivot = start;
		
		/*
		 * smaller - pokazivac na koren liste koja sadrzi elemente manje od
		 * pivota; greater - pokazivac na koren liste koja sadrzi one elem-
		 * ente koji su veci od pivota
		 */ 
		Node smaller = null, greater = null;
		
		Node current = pivot.next;
		
		while (current != null) {
			Node afterCurrent = current.next;
			
			if (current.info.compareTo(pivot.info) < 0) {
				// dodajemo current na pocetak liste smaller
				current.next = smaller;
				smaller = current;
			} else {
				// dodajemo current na pocetak liste greater
				current.next = greater;
				greater = current;
			}
			
			current = afterCurrent;
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
}
