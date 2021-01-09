package p_vezba.qA12_open_hash_set;

public class OHashSet<T> {

	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	
	private static class Node {
		
		Object value;
		Node next;
		
		public Node(Object value) {
			this.value = value;
		}
	}
	
	
	private Node[] table;
	
	
	public OHashSet(int size) 	{ this.table = new Node[size]; 	}
	public OHashSet() 			{ this(DEFAULT_TABLE_SIZE); 	}
	
	
	private int hash(T o) {
		return Math.abs(o.hashCode() % table.length);
	}
	
	
	private Node[] searchCollisionChain(T element, int hashValue) {
		
		Node curr = table[hashValue];
		Node prev = null;
		
		while (curr != null) {
			if (curr.value.equals(element)) {
				return new Node[] { curr, prev };
			}
			prev = curr;
			curr = curr.next;
		}
		
		return null;
	}
	
	
	public boolean member(T element) {
		return searchCollisionChain(element, hash(element)) != null;
	}
	
	
	public boolean insert(T element) {
		
		if (member(element)) return false;
		
		Node newNode = new Node(element);
		newNode.next = table[hash(element)];
		table[hash(element)] = newNode;
		
		return true;
	}
	
	
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
	
	// Samo za potrebe demonstracije!
	public void print() {
		for (int i = 0; i < table.length; i++) {
			System.out.print("HashCode = " + i + ": ");
			
			Node head = table[i];
			if (head == null) {
				System.out.println("empty");
			} else {
				while (head != null) {
					System.out.print("[" + head.value + "] ");
					head = head.next;
				}
				System.out.println();
			}
		}
	}
	
	// Samo za potrebe demonstracije!
	public static void main(String[] args) {
		OHashSet<Integer> set = new OHashSet<Integer>(5);
		
		for (int i = 0; i < 24; i++)
			set.insert(i);
		set.print();
		
		if (set.insert(10))
			System.out.println("10 je ubacen u skup");
		else
			System.out.println("10 je vec u skupu");
		
		System.out.println("Member(10) - " + set.member(10) + ", Member(34) - " + set.member(34));
		
		System.out.println("Remove(22) - " + set.remove(22));
		System.out.println("Member(22) - " + set.member(22));
		set.print();
	}
}
