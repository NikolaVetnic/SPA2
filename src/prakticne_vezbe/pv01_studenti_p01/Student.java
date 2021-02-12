package prakticne_vezbe.pv01_studenti_p01;

public class Student implements Comparable<Student> {
	
	final private String prezime;
	final private String ime;
	final private int god;
	
	public Student(String prezime, String ime, int god) {
		this.prezime = prezime;
		this.ime = ime;
		this.god = god;
	}
	
	public String toString() {
		return prezime + " " + ime + ", godina upisa: " + god;
	}

	@Override
	public int compareTo(Student that) {
		
		
//		return this.god - that.god;
		
//		return this.prezime.compareTo(that.prezime) != 0 ?
//			   this.prezime.compareTo(that.prezime) : this.ime.compareTo(that.ime);
			   
		return this.god - that.god != 0 ? 
			   this.god - that.god : this.prezime.compareTo(that.prezime) != 0 ?
					   				 this.prezime.compareTo(that.prezime) : this.ime.compareTo(that.ime);
	}

	public String getPrezime() {
		return prezime;
	}

	public String getIme() {
		return ime;
	}

	public int getGod() {
		return god;
	}
}
