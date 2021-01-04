package p_vezba.p05_hash_tables_p01;

public interface Map<K, V> {

	boolean insert(K key, V value);
	
	boolean delete(K key);
	
	V get(K key);
	
	boolean modify(K key, V value);
}
