package p_vezba.p05_hash_tables_p01;

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
	
	public OHashMap(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("Velicina hash mape ne moze biti negativna");
		
		this.table = new Node[size];
	}
	
	public OHashMap() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public int hash(Object o) {
		if (o == null)
			throw new IllegalArgumentException("Hash f-ja se ne moze racunati za null objekat");
		
		return Math.abs(o.hashCode() % table.length);
	}
	
	private Node[] searchCollisionChain(K key, int hashValue) {
		
		Node prev = null;
		Node curr = table[hashValue];
		
		while (curr != null) {
			
			if (curr.key.equals(key)) {
				return new Node[] { curr, prev };
			}
			
			prev = curr;
			curr = curr.next;
		}
		
		return null;
	}

	@Override
	public boolean insert(K key, V value) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n != null) return false;
		
		Node newElement = new Node(key, value);
		
		newElement.next = table[hashValue];
		table[hashValue] = newElement;
		
		return true;
	}

	@Override
	public boolean delete(K key) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null) return false;
		
		if (n[0] == table[hashValue])
			table[hashValue] = table[hashValue].next;
		else
			n[1].next = n[0].next;
		
		return true;
	}

	@Override
	public V get(K key) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null) return null;
		
		if (n[0] != table[hashValue]) {
			n[1].next = n[0].next;
			n[0].next = table[hashValue];
			table[hashValue] = n[0];
		}
		
		return (V) n[0].value;
	}

	@Override
	public boolean modify(K key, V value) {
		
		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null) return false;
		
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
