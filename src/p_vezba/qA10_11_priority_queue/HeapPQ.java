package p_vezba.qA10_11_priority_queue;

import java.util.ArrayList;

public class HeapPQ<T extends Comparable<T>> {

	private static final int DEFAULT_INITIAL_CAPACITY = 100;
	
	private ArrayList<T> queue;
	
	public HeapPQ() {
		queue = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
	}
	
	
	void insert(T element) {
		
		queue.add(element);
		restoreHeapProperty(size() - 1);
	}


	private void restoreHeapProperty(int sonIndex) {
		
		int parentIndex = (sonIndex - 1) / 2;
		boolean heapRestored = false;
		
		while (!heapRestored && sonIndex > 0) {
			
			T parent = queue.get(parentIndex);
			T son = queue.get(sonIndex);
			
			if (parent.compareTo(son) > 0) {
				heapRestored = true;
			} else {
				swap(sonIndex, parentIndex);
				
				sonIndex = parentIndex;
				parentIndex = (sonIndex - 1) / 2;
			}
		}
	}


	private void swap(int idxA, int idxB) {
		T tmp = queue.get(idxA);
		queue.set(idxA, queue.get(idxB));
		queue.set(idxB, tmp);
	}
	
	
	public T max() {
		return queue.get(0);
	}
	
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	public T delMax() {
		T out = this.max();
		swap(0, size() - 1);
		queue.remove(size() - 1);
		restoreHeapProperty();
		
		return out;
	}
	
	
	private void restoreHeapProperty() {
		
		boolean heapRestored = false;
		int parentIndex = 0;
		
		while (!heapRestored) {
			
			int maxSonIndex = getMaxSon(parentIndex);
			
			if (maxSonIndex == -1) {
				heapRestored = true;
			} else {
				if (queue.get(maxSonIndex).compareTo(queue.get(parentIndex)) > 0) {
					swap(maxSonIndex, parentIndex);
					parentIndex = maxSonIndex;
				} else {
					heapRestored = true;
				}
			}
		}
	}


	private int getMaxSon(int parentIndex) {
		
		int son1Index = 2 * parentIndex + 1;
		int son2Index = 2 * parentIndex + 2;
		int maxSonIndex = -1;
		
		if (son1Index < size())
			maxSonIndex = son1Index;
		
		if (son2Index < size())
			if (queue.get(son2Index).compareTo(queue.get(maxSonIndex)) > 0)
				maxSonIndex = son2Index;
		
		return maxSonIndex;
	}
	
	
	public int size() {
		return queue.size();
	}


	public static void main(String[] args) {
		
		HeapPQ<Integer> pq = new HeapPQ();
		
		pq.insert(3);
		pq.insert(5);
		pq.insert(6);
		System.out.println("Max : " + pq.max() + ", size : " + pq.size());
		
		if (!pq.isEmpty()) System.out.println("Nije prazna.");
		
		System.out.println("Izbacujem : " + pq.delMax());
		System.out.println("Max : " + pq.max() + ", size : " + pq.size());
	}
}
