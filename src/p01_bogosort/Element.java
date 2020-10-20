package p01_bogosort;

public class Element implements Comparable<Element> {
	
	private int info;
	
	
	public Element(int info) {
		this.info = info;
	}
	
	
	public String toString() {
		return info + "";
	}
	
	
	public int getInfo() {	return info; }
	
	
	public int compareTo(Element e) {
		return info - e.info;
	}
}
