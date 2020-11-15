package pv01_studenti_p02;

public class Student implements Comparable<Student> {

	private String prz, ime;
	private int god;
	
	public Student(String prz, String ime, int god) {
		this.prz = prz;
		this.ime = ime;
		this.god = god;
	}

	@Override
	public String toString() {
		return prz + " " + ime + ", " + god;
	}

	public String prz() 	{ return prz; }
	public String ime() 	{ return ime; }
	public int god() 		{ return god; }

	@Override
	public int compareTo(Student that) {
		return god - that.god;
	}
}
