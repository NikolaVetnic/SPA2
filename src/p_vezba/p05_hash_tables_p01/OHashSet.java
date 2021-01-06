package p_vezba.p05_hash_tables_p01;

public class OHashSet<T> implements Set<T> {
	
	public static final int DEFAULT_TABLE_SIZE = 101;
	
	static class Node {
		
		Object element;
		Node next;
		
		public Node(Object value) {
			this.element = value;
		}
	}
	
	Node[] table;
	
	public OHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public OHashSet(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("Negative size");
		
		this.table = new Node[size];
	}
	
	public int hash(T o) {
		return Math.abs(o.hashCode() % table.length); 
	}
	
	private Node[] searchCollisionChain(T element, int hashValue) {
		
		Node curr = table[hashValue];
		Node prev = null;
		
		while (curr != null) {
			
			if (curr.element.equals(element))
				return new Node[] { curr, prev };
			
			prev = curr;
			curr = curr.next;
		}
		
		return null;
	}

	@Override
	public boolean insert(T element) {
		
		int hashValue = hash(element);
		
		Node[] n = searchCollisionChain(element, hashValue);
		
		if (n != null)
			return false;
		
		Node newElement = new Node(element);
		newElement.next = table[hashValue];
		table[hashValue] = newElement;
		
		return true;
	}

	@Override
	public boolean remove(T element) {
		
		int hashValue = hash(element);
		
		Node[] n = searchCollisionChain(element, hashValue);
		
		if (n == null)
			return false;
		
		if (n[0] == table[hashValue]) {
			table[hashValue] = table[hashValue].next;
		} else {
			n[1].next = n[0].next;
		}
		
		return true;
	}

	@Override
	public boolean member(T element) {
		return searchCollisionChain(element, hash(element)) != null;
	}

	// Stampanje skupa.
	public void print() {
		for (int i = 0; i < table.length; i++) {
			System.out.print("HashCode = " + i + ": ");
			
			Node head = table[i];
			if (head == null) {
				System.out.println("empty");
			} else {
				while (head != null) {
					System.out.print("[" + head.element + "] ");
					head = head.next;
				}
				System.out.println();
			}
		}
	}
}
