package p02_priorityqueue;

public interface PriorityQueue<T extends Comparable<T>> {

	/** Dodaje novi element u prioritetnu listu. */
	void insert(T element);
	
	/** Vraca element sa najvecim prioritetom. */
	T max();
	
	/** Vraca i brise iz prioritetne liste element sa najvecim pri-
	 * oritetom. 
	 */
	T delMax();
	
	/** Da li je prioritetna lista prazna? */
	boolean isEmpty();
	
	/** Vraca velicinu prioritetne liste. */
	int size();
}
