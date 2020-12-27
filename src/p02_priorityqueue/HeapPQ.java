package p02_priorityqueue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapPQ<T extends Comparable<T>> implements PriorityQueue<T> {
	
	
	private static final int DEFAULT_INITIAL_CAPACITY = 100;
	
	
	private ArrayList<T> queue;
	
	
	public HeapPQ(int initialCapacity) {
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("initial capacity <= 0 ?!");
		
		queue = new ArrayList<>(initialCapacity);
	}
	
	public HeapPQ() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	

	/*
	 * Dodaj novi element na kraj (prosirivog) niza i nakon toga popra-
	 * vi niz tako da ponovo ima hip strukturu: 1) ako je dodati eleme-
	 * nt veci od oca razmenih ih, i 2) ponavljaj prethodni korak dokl-
	 * e god je zadovoljen uslov razmene ili dok ne stignes do korensk-
	 * og cvora.
	 */
	
	@Override
	public void insert(T element) {
		queue.add(element);
		restoreHeapProperty(queue.size() - 1);
	}
	

	private void restoreHeapProperty(int sonIndex) {
		boolean heapRestored = false;
		int parentIndex = (sonIndex - 1) / 2;
		while (!heapRestored && sonIndex > 0) {
			T parent = queue.get(parentIndex);
			T son = queue.get(sonIndex);
			
			// da li je otac veci od sina?
			if (parent.compareTo(son) > 0) {
				heapRestored = true;
			} else {
				// razmeni oca sa sinom
				swap(sonIndex, parentIndex);
				
				// nastavi dalje ka korenu: sin je sada na poziciji oca i on i-
				// ma sada novog oca
				sonIndex = parentIndex;
				parentIndex = (sonIndex - 1) / 2;
			}
		}
	}
	

	@Override
	public T max() {
		if (queue.size() == 0)
			throw new NoSuchElementException("empty queue");
		
		return queue.get(0);
	}
	
	
	/*
	 * Poslednji element niza stavi na pocetak niza, povrati hip struk-
	 * turu, ovoga puta od korena ka kraju niza: 1) ukoliko je otac ma-
	 * nji od veceg sina razmeni ih, 2) ponavljaj prethodni korak dokle
	 * god otac ima bar jednog sina i dokle god je uslov razmene zadov-
	 * oljen.
	 */ 

	@Override
	public T delMax() {
		if (queue.size() == 0)
			throw new NoSuchElementException("empty queue");
		
		T res = queue.get(0);
		swap(0, queue.size() - 1);
		queue.remove(queue.size() - 1);
		
		restoreHeapProperty();
		
		return res;
	}
	

	private void restoreHeapProperty() {
		boolean heapRestored = false;
		int parentIndex = 0;
		
		while (!heapRestored) {
			int maxSonIndex = getMaxSon(parentIndex);
			// ne postoji nijedan od sinova
			if (maxSonIndex == -1) {
				heapRestored = true;
			} else {
				T parent = queue.get(parentIndex);
				T maxSon = queue.get(maxSonIndex);
				
				// da li je otac veci od veceg sina?
				if (parent.compareTo(maxSon) > 0)
					heapRestored = true;
				else {
					// razmeni oca sa sinom
					swap(parentIndex, maxSonIndex);
					
					// otac je sada na poziciji sina (i ima nove sinove)
					parentIndex = maxSonIndex;
				}
			}
		}
	}
	

	private int getMaxSon(int parentIndex) {
		int son1Index = 2 * parentIndex + 1;
		int son2Index = 2 * parentIndex + 2;
		int maxSonIndex = -1;
		
		// postoji sin1?
		if (son1Index < queue.size()) {
			maxSonIndex = son1Index;
		}
		
		// postoji sin2?
		if (son2Index < queue.size()) {
			// da li drugi sin ima veci prioritet
			T s1 = queue.get(son1Index);
			T s2 = queue.get(son2Index);
			if (s2.compareTo(s1) > 0)
				maxSonIndex = son2Index;
		}
		
		return maxSonIndex;
	}
	

	@Override
	public boolean isEmpty() {
		return queue.size() == 0;
	}
	

	@Override
	public int size() {
		return queue.size();
	}
	
	
	private void swap(int indexa, int indexb) {
		T a = queue.get(indexa);
		queue.set(indexa, queue.get(indexb));
		queue.set(indexb, a);
	}
}
