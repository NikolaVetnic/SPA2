package p01_comparable;

public class Person implements Comparable<Person> {
	
	private String firstName, lastName;
	
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public int compareTo(Person o) {
		 
		int cmpLastNames = lastName.compareTo(o.lastName);
		
		if (cmpLastNames != 0)
			return cmpLastNames;
		else
			return firstName.compareTo(o.firstName);
	}
	
	
	public String toString() {
		return lastName + " " + firstName;
	}
}