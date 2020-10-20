package p01_comparator;

import java.util.Comparator;

class SortByName implements Comparator<Student> {
	
	public int compare(Student a, Student b) {
		return a.name.compareTo(b.name);
	}
}