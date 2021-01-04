package p_vezba.p05_hash_tables_p01;

public class CHashSet<T> implements Set<T> {
	
	private static final int DEFAULT_TABLE_SIZE = 101;
	
	enum Status {
		EMPTY, OCCUPIED, DELETED;
	}
	
	private Object[] table;
	private Status[] status;
	private int numElements;
	
	public CHashSet(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("Size must be a pozitive value");
		
		reset(size);
	}
	
	public CHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	private int hash(Object o) {
		if (o == null)
			throw new IllegalArgumentException("Cannot calculate hashCode() for null");
		
		return Math.abs(o.hashCode() % table.length);
	}
	
	private void reset(int size) {
		this.table = new Object[size];
		this.status = new Status[size];
		
		for (int i = 0; i < size; i++)
			status[i] = Status.EMPTY;
		
		this.numElements = 0;
	}
	
	private int searchCollisionChain(T element, int hashValue) {
		
		int currentPos = hashValue;
		int i = 0;
		int maxChainLength = (table.length - 1) / 2;
		
		while (i < maxChainLength && status[currentPos] != Status.EMPTY) {
			
			if (status[currentPos] == Status.OCCUPIED && table[currentPos].equals(element))
				return currentPos;
			
			i++;
			currentPos = (hashValue + i * i) % table.length;
		}
		
		return -1;
	}

	@Override
	public boolean insert(T element) {
		
		int hashValue = hash(element);
		
		int maxChainLength = (table.length - 1) / 2;
		int i = 0;
		boolean endOfChain = false;
		int firstAvailablePos = -1;
		
		while (!endOfChain && i < maxChainLength) {
			
			int currentPos = (hashValue + i * i) % table.length;
			
			if (status[currentPos] == Status.OCCUPIED) {
				if (table[currentPos].equals(element))
					return false;
			} else {
				if (firstAvailablePos == -1)
					firstAvailablePos = currentPos;
				
				if (status[currentPos] == Status.EMPTY)
					endOfChain = true;
			}
			
			i++;
		}
		
		if (firstAvailablePos == -1 || loadFactor() > 0.7) {
			expand();
			hashValue = hash(element);
			add(element, hashValue);
		} else {
			table[firstAvailablePos] = element;
			status[firstAvailablePos] = Status.OCCUPIED;
			numElements++;
		}
		
		return true;
	}

	private void add(T element, int hashValue) {
		
		boolean foundPosition = false;
		int i = 0;
		
		while (!foundPosition) {
			
			int currentPos = (hashValue + i * i) % table.length;
			
			if (status[currentPos] != Status.OCCUPIED) {
				
				status[currentPos] = Status.OCCUPIED;
				table[currentPos] = element;
				numElements++;
				foundPosition = true;
			} else {
				i++;
			}
		}
	}

	private void expand() {
		
		int oldSize = table.length;
		int size = 2 * oldSize;
		
		while (!isPrime(size))
			size++;
		
		Object[] tableOld = new Object[oldSize];
		Status[] statusOld = new Status[oldSize];
		
		for (int i = 0; i < oldSize; i++) {
			tableOld[i] = table[i];
			statusOld[i] = status[i];
		}
		
		reset(size);
		
		for (int i = 0; i < oldSize; i++)
			if (statusOld[i] == Status.OCCUPIED) {
				T elem = (T) tableOld[i];
				add(elem, hash(elem));
			}
		
		tableOld = null;
		statusOld = null;
	}

	private boolean isPrime(int num) {
		for (int i = 2; i * i < num; i++)
			if (num % i == 0)
				return false;
		
		return true;
	}

	private double loadFactor() {
		return (double) numElements / (double) table.length;
	}

	@Override
	public boolean remove(T element) {
		
		int pos = searchCollisionChain(element, hash(element));
		if (pos <= 0) return false;
		
		status[pos] = Status.DELETED;
		numElements--;
		
		return true;
	}

	@Override
	public boolean member(T element) {
		return searchCollisionChain(element, hash(element)) > 0;
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
}
