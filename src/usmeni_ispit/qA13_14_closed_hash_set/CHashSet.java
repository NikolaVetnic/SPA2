package usmeni_ispit.qA13_14_closed_hash_set;

public class CHashSet<T> {

	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	
	private enum Status {
		EMPTY, OCCUPIED, DELETED
	};
	
	
	private Object[] table;
	private Status[] status;
	private int numElements;
	
	
	public CHashSet(int size) {
		reset(size);
	}
	
	
	private void reset(int size) {
		this.table = new Object[size];
		this.status = new Status[size];
		
		for (int i = 0; i < status.length; i++)
			status[i] = Status.EMPTY;
		
		this.numElements = 0;
	}


	public CHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	
	private int hash(Object o) {
		return Math.abs(o.hashCode() % table.length);
	}
	
	
	private int searchCollisionChain(T element, int hashValue) {
		
		int currentPosition = hashValue;
		int i = 0;
		int maxChainLength = (table.length - 1) / 2;
		
		while (i <= maxChainLength && status[currentPosition] != Status.EMPTY) {
			
			if (status[currentPosition] == Status.OCCUPIED && table[currentPosition].equals(element))
				return currentPosition;
			
			i++;
			currentPosition = (hashValue + i * i) % table.length;
		}
		
		return -1;
	}
	
	
	public boolean insert(T element) {
		
		int hashValue = hash(element);
		
		int maxChainLength = (table.length - 1) / 2;
		int i = 0;
		boolean endOfChain = false;
		int firstAvailablePosition = -1;
		
		while (!endOfChain && i <= maxChainLength) {
			
			int currentPosition = (hashValue + i * i) % table.length;
			
			if (status[currentPosition] == Status.OCCUPIED) {
				if (table[currentPosition].equals(element))
					return false;
			} else {
				if (firstAvailablePosition == -1)
					firstAvailablePosition = currentPosition;
				
				if (status[currentPosition] == Status.EMPTY)
					endOfChain = true;
			}
			
			i++;
		}
		
		if (firstAvailablePosition == -1 || loadFactor() > 0.7) {
			expand();
			hashValue = hash(element);
			add(element, hashValue);
		} else {
			status[firstAvailablePosition] = Status.OCCUPIED;
			table[firstAvailablePosition] = element;
			numElements++;
		}
		
		return true;
	}
	
	
	private void expand() {
		
		int oldSize = table.length;
		int size = 2 * oldSize;
		
		while (!isPrime(size)) size++;
		
		System.out.println("Expanding to size - " + size);
		
		Object[] oldTable = new Object[oldSize];
		Status[] oldStatus = new Status[oldSize];
		
		for (int i = 0; i < oldSize; i++) {
			oldTable[i] = table[i];
			oldStatus[i] = status[i];
		}
		
		reset(size);
		
		for (int i = 0; i < oldSize; i++) {
			if (oldStatus[i] == Status.OCCUPIED) {
				T elem = (T) oldTable[i];
				add(elem, hash(elem));
			}
		}
		
		oldTable = null;
		oldStatus = null;
	}


	private void add(T element, int hashValue) {
		
		boolean foundPosition = false;
		int i = 0;
		
		while (!foundPosition) {
			
			int currentPosition = hashValue + i * i;
			
			if (status[currentPosition] != Status.OCCUPIED) {
				
				status[currentPosition] = Status.OCCUPIED;
				table[currentPosition] = element;
				numElements++;
				foundPosition = true;
			} else {
				i++;
			}
		}
	}


	private boolean isPrime(int num) {
		
		for (int i = 2; i * i < num; i++)
			if (num % i == 0) return false;
		
		return true;
	}


	private double loadFactor() {
		return (double) numElements / (double) table.length;
	}


	public boolean member(T element) {
		return searchCollisionChain(element, hash(element)) >= 0;
	}
	
	
	public boolean remove(T element) {
		
		int pos = searchCollisionChain(element, hash(element));
		if (pos < 0) return false;
		
		status[pos] = Status.DELETED;
		numElements--;
		
		return true;
	}
	

	public void print() {
		
		for (int i = 0; i < table.length; i++) {
			if (status[i] == Status.EMPTY)
				System.out.println("[E]");
			else if (status[i] == Status.DELETED)
				System.out.println("[D]");
			else
				System.out.println("[O] - " + table[i] + "]");
		}
		
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		CHashSet<Integer> set = new CHashSet<Integer>(11);
		
		// formiramo brojeve koji imaju istu hash vrednost - 2
		for (int i = 0; i < 5; i++) {
			int num = 2 + 11 * i;
			System.out.println("Dodajem: " + num);
			set.insert(num);
			set.print();
		}
	}
}
