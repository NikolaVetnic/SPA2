package prakticne_vezbe.pv02_sort_p01;

public class Student implements Comparable<Student> {
	
	final private String prezime, ime;
	final private int god;
	
	private Student(String prezime, String ime, int god) {
		this.prezime = prezime;
		this.ime = ime;
		this.god = god;
	}
	
	public static Student novi(String prezime, String ime, int godina) {
		return new Student(prezime, ime, godina);
	}
	
	public String toString() {
		return prezime + " " + ime + ", godina upisa: " + god;
	}
	
	@Override
	public int compareTo(Student that) {
		
		return this.prezime().compareTo(that.prezime()) != 0 ? 
			   this.prezime().compareTo(that.prezime()) : this.ime().compareTo(that.ime());
	}

	public String prezime() { return prezime; 	}
	public String ime() 	{ return ime; 		}
	public int godina() 	{ return god; 		}
}
