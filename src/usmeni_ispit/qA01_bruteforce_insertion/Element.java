package usmeni_ispit.qA01_bruteforce_insertion;

public class Element implements Comparable<Element> {
		
	int n;
	
	public Element(int n) { 
		this.n = n; 
	}

	@Override
	public int compareTo(Element o) { 
		return this.n - o.n; 
	}
	
	@Override
	public String toString() { 
		return " " + n; 
	}
}
