package p05_hash_tables;

public interface Set<T> {

	/**
	 * Dodaje element u skup. Vraca false ukoliko element vec postoji u
	 * skupu.
	 */
	boolean insert(T element);
	
	/**
	 * Brise element iz skupa. Vraca false ukoliko element ne postoji u
	 * skupu.
	 */
	boolean remove(T element);
	
	/**
	 * Proverava da li je element u skupu.
	 */
	boolean member(T element);
}
