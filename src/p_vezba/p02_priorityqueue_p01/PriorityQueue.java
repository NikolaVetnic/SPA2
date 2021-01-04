package p_vezba.p02_priorityqueue_p01;

public interface PriorityQueue<T extends Comparable<T>> {

	void insert(T element);
	
	T max();
	
	T delMax();
	
	boolean isEmpty();
	
	int size();
}
