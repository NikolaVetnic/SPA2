package p_vezba.qA01_bruteforce_insertion;

public class IntWrap implements Comparable<IntWrap> {
		
	int n;
	
	public IntWrap(int n) { 
		this.n = n; 
	}

	@Override
	public int compareTo(IntWrap o) { 
		return this.n - o.n; 
	}
	
	@Override
	public String toString() { 
		return " " + n; 
	}
}
