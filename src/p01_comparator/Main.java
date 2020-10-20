package p01_comparator;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Student> ar = new ArrayList<Student>();
		ar.add(new Student(111, "Petar Petrovic", "Novi Sad"));
		ar.add(new Student(131, "Milan Milanov", "Sremska Kamenica"));
		ar.add(new Student(121, "Bojan Bojanic", "Veternik"));
		
		System.out.println("Nesortirano: ");
		for (int i = 0; i < ar.size(); i++)
			System.out.println(ar.get(i));
			
		Collections.sort(ar, new SortByRoll());
		
		System.out.println("Sortirano prema broju: ");
		for (int i = 0; i < ar.size(); i++)
			System.out.println(ar.get(i));
			
		Collections.sort(ar, new SortByName());
		
		System.out.println("Sortirano prema imenu: ");
		for (int i = 0; i < ar.size(); i++)
			System.out.println(ar.get(i));
	}
}
