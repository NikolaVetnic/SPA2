package p01_comparator;

class Student {
	
	int rollno;
	String name, address;
	
	
	public Student(int rollno, String name, String address) {
		this.rollno = rollno;
		this.name = name;
		this.address = address;
	}
	
	
	public String toString() {
		return rollno + " " + name + " " + address; 
	}
}
