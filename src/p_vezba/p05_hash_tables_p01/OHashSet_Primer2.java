package p_vezba.p05_hash_tables_p01;

public class OHashSet_Primer2 {

	/*
	 * Da bi se napravio skup objekata klase A koristeci OHashSet klasa
	 * A mora da redefinise sledece metode iz klase Object:
	 * 1. int hashCode() - koristi se pri racunanju vrednosti hash f-je
	 * 2. boolean equals(Object o) - metode member i remove koriste eq-
	 *    uals za poredjenje elemenata
	 * 3. String toString() - metoda print koristi S.O.P. koji opet ko-
	 *    risti toString
	 */
	static class Student {
		
		private String index, fName, lName;
		
		public Student(String index, String fName, String lName) {
			this.index = index;
			this.fName = fName;
			this.lName = lName;
		}
		
		public String toString() {
			return index + ", " + fName + " " + lName;
		}
		
		public boolean equals(Object anotherStudent) {
			if (anotherStudent != null && anotherStudent instanceof Student) {
				
				Student s = (Student) anotherStudent;
				return index.equals(s.index) && fName.equals(s.fName) && lName.equals(s.lName);
			} else {
				return false;
			}
		}
		
		public int hashCode() {
			return toString().hashCode();
		}
	}
	
	public static void main(String[] args) {
		
		OHashSet<Student> s = new OHashSet<Student>(5);
		s.insert(new Student("264/03", "Stojan", "Markovic"));
		s.insert(new Student("145/03", "Jovana", "Lackovic"));
		s.insert(new Student("264/03", "Stojan", "Markovic"));
		s.print();
	}
}
