package p05_hash_tables;

public interface Map<K, V> {

	/**
	 * Dodaje novi par (key, value) u mapu. Vraca false ukoliko key vec
	 * postoji u mapi.
	 */
	boolean insert(K key, V value);
	
	/**
	 * Brise par (key, value) iy mape. Vraca false ukoliko key ne posto
	 * ji u mapi.
	 */
	boolean delete(K key);
	
	/**
	 * Vraca value vezan za key.
	 */
	V get(K key);
	
	/**
	 * Modifikuje value vezan za key. Vraca false ukoliko key ne postoj
	 * i u mapi.
	 */
	boolean modify(K key, V value);
}
