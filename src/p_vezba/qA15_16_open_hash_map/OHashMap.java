package p_vezba.qA15_16_open_hash_map;

public class OHashMap<K, V> {

	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	
	static class Node {
		
		Object key, value;
		Node next;
		
		public Node(Object key, Object value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	
	
	private Node[] table;
	
	
	public OHashMap(int size) 	{ this.table = new Node[size]; 	}
	public OHashMap() 			{ this(DEFAULT_TABLE_SIZE); 	}
	
	
	private int hash(K o) {
		return Math.abs(o.hashCode() % table.length);
	}
	
	
	private Node[] searchCollisionChain(K key, int hashValue) {
		
		Node prev = null, curr = table[hashValue];
		
		while (curr != null) {
			if (curr.key.equals(key))
				return new Node[] { curr, prev };
			
			prev = curr;
			curr = curr.next;
		}
		
		return null;
	}
	
	
	public V get(K key) {
		
		int hashValue = hash(key);
		Node[] n = searchCollisionChain(key, hashValue);
		
		if (n == null) return null;
		
		if (n[0] != table[hashValue]) {
			n[1].next = n[0].next;
			n[0].next = table[hashValue];
			table[hashValue] = n[0];
		}
		
		return (V) table[hashValue].value;
	}
	
	
	public boolean insert(K key, V value) {
		
		int hashValue = hash(key);
		
		if (searchCollisionChain(key, hashValue) != null)
			return false;
		
		Node newNode = new Node(key, value);
		newNode.next = table[hashValue];
		table[hashValue] = newNode;
		
		return true;
	}
	
	
	public boolean delete(K key) {

		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null)
			return false;
		
		if (n[0] == table[hashValue])
			table[hashValue] = table[hashValue].next;
		else
			n[1].next = n[0].next;
		
		return true;
	}
	
	
	public boolean modify(K key, V value) {

		int hashValue = hash(key);
		
		Node[] n = searchCollisionChain(key, hashValue);
		if (n == null) 
			return false;
		
		n[0].value = value;
		return true;
	}
}
