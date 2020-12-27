package p03_advanced_sorts;

public class Element implements Comparable<Element> {

	private int info;
	
	public Element(int info) {
		this.info = info;
	}
	
	public String toString() {
		return "" + info;
	}
	
	public int info() 	{ return info;	}

	@Override
	public int compareTo(Element e) {
		return info - e.info;
	}
}
