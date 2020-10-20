package p01_comparable;

public class Main {

	public static void main(String[] args) {
		
		Person p1 = new Person("Milan", "Mihajlovic");
		Person p2 = new Person("Dragoljub", "Mihajlovic");
		Person p3 = new Person("Petar", "Petrovic");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		System.out.println("Poredjenje osoba #1 i #2: " + p1.compareTo(p2));
		System.out.println("Poredjenje osoba #1 i #3: " + p1.compareTo(p3));
		System.out.println("Poredjenje osoba #2 i #3: " + p2.compareTo(p3));
	}
}
