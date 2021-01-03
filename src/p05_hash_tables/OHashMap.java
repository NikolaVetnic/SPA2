package p05_hash_tables;

public class OHashMap<K, V> implements Map<K, V> {
	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	private static class Node {
		
		Object key;
		Object value;
		Node next;
		
		public Node(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private Node[] table;
	
	public OHashMap() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public OHashMap(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("Velicina hash mape ne moze biti negativna");
		
		table = new Node[size];
	}
	
	private int hash(K o) {
		if (o == null)
			throw new IllegalArgumentException("Hash f-ja se ne moze racunati za null objekat");
		
		return Math.abs(o.hashCode() % table.length);
	}
	
	/**
	 * Pretrazuje listu kolizija trazeci element odredjen kljucem. Ukol
	 * iko element postoji u tabeli tada se vraca niz od dve reference:
	 * <br>
	 * - prva je pokazivac na element u listi kolizija<br>
	 * - druga je pokazivac na prethodni element
	 */
	private Node[] searchCollisionChain(K key, int hashValue) {
		
		Node current = table[hashValue];
		Node prev = null;
		
		while (current != null) {
			if (current.key.equals(key)) {
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
	
	/*
	 * U praksi se cesto desava da postoje takvi elementi koji se doba-
	 * vljaju mnogo cesce u odnosu na prosek (hot data i cold data). L-
	 * east recently used (LRU) strategija - poslednji se trazeni elem-
	 * ent pomeri na pocetak lanca kolizija.
	 */
	@Override
	public V get(K key) {
		
		int hashValue = hash(key);
		Node[] n = searchCollisionChain(key, hashValue);
		
		if (n == null)
			return null;
		
		// LRU strategija - stavi element na pocetak lanca kolizija
		if (n[0] != table[hashValue]) {
			n[1].next = n[0].next;			// prevezivanje n[0]
			n[0].next = table[hashValue];	// n[0] se stavlja pre pocetka lanca kolizije
			table[hashValue] = n[0];		// pokazivac na pocetak lanca se azurira
		}
		
		return (V) n[0].value;
	}

	@Override
	public boolean insert(K key, V value) {
		
		int hashValue = hash(key);
		
		if (searchCollisionChain(key, hashValue) != null)
			return false;
		
		// Ubacivanje novog elementa na pocetak lanca kolizije.
		Node newElement = new Node(key, value);
		newElement.next = table[hashValue];
		table[hashValue] = newElement;
		
		return true;
	}

	@Override
	public boolean delete(K key) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null)
			return false;
		
		if (n[0] == table[hashValue])	// trazeni element je na pocetku lanca
			table[hashValue] = table[hashValue].next;
		else
			n[1].next = n[0].next;		// trazeni element nije na pocetku lanca
		
		return true;
	}

	@Override
	public boolean modify(K key, V value) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null) 
			return false;
		
		n[0].value = value;
		return true;
	}

	public void print() {
		for (int i = 0; i < table.length; i++) {
			System.out.print("HashCode = " + i + ": ");
			
			Node head = table[i];
			if (head == null) {
				System.out.println(" empty");
			} else {
				while (head != null) {
					System.out.print("[" + head.key + ": " + head.value + "]");
					head = head.next;
				}
				
				System.out.println();
			}
		}
	}
}
