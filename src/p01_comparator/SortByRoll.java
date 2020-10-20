package p01_comparator;

import java.util.Comparator;

class SortByRoll implements Comparator<Student> {
	
	public int compare(Student a, Student b) {
		return a.rollno - b.rollno;
	}
}