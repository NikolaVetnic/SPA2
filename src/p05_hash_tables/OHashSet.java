package p05_hash_tables;

public class OHashSet<T> implements Set<T> {

	private static final int DEFAULT_TABLE_SIZE = 101;
	
	/*
	 * Cvor u listi objekata sa istom hash vrednoscu; Object value - o-
	 * bjekat, Node next - referenca na sledeci objekat u listi.
	 */
	private static class Node {
		
		Object value;
		Node next;
		
		public Node(Object value) {
			this.value = value;
		}
	}
	
	// Niz listi.
	private Node[] table;
	
	// Konstruktori.
	public OHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public OHashSet(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("Velicina hash tabele ne moze biti negativna");
		
		table = new Node[size];
	}
	
	// Hash f-ja.
	private int hash(T o) {
		if (o == null)
			throw new IllegalArgumentException("Hash f-ja se ne moze racunati za null objekat");
		
		return Math.abs(o.hashCode() % table.length);
	}
	
	/**
	 * Pretrazuje listu kolizija za datu vrednost hash f-je (drugi argu
	 * ment metoda) trazeci element (prvi argument metoda). Metod vraca
	 *  null ukoliko element ne postoji u tabeli, ili niz od dve refere
	 * nce:<br>
	 * 1. prva referenca je na trazeni element<br>
	 * 2. druga referenca je referenca na element ispred trazenog
	 */ 
	private Node[] searchCollisionChain(T element, int hashValue) {
		
		Node current = table[hashValue];
		Node prev = null;
		
		while (current != null) {
			if (current.value.equals(element)) {
				Node[] ret = new Node[2];
				ret[0] = current;
				ret[1] = prev;
				return ret;
			}
			
			prev = current;
			current = current.next;
		}
		
		return null;
	}
	
	@Override
	public boolean member(T element) {
		return searchCollisionChain(element, hash(element)) != null;
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

	// Stampanje skupa.
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
}
