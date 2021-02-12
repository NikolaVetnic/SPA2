package prakticne_vezbe.pv02_sort_p01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void sortirajNiz(Student[] studenti, Comparator<Student> c) {
		
		for (int i = studenti.length - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (c.compare(studenti[j], studenti[i]) > 0) {
					Student tmp = studenti[j];
					studenti[j] = studenti[i];
					studenti[i] = tmp;
				}
	}
	
	public static void sortMenu(Generacija g) {
		
		int 	op1; 
		boolean op2;
		
		Scanner sc = new Scanner(System.in);
		
		do {

			op1 = 0;	op2 = false;
			
			System.out.println("\nOdaberite nacin sortiranja: ");
			System.out.println("  [1] leksikografski po prezimenima i imenima studenata,");
			System.out.println("  [2] po godini i prezimenu studenta");
			System.out.println("  [3] po ukupnoj duzini prezimena i imena");
			System.out.println("  [4] kraj rada");
			
			do {
				
				System.out.print("Izbor : ");
				op1 = sc.nextInt();
				
				if (0 >= op1 && op1 > 4) System.out.println("Nepostojeca komanda.");
				
			} while (0 >= op1 && op1 > 4);
			
			if (op1 == 2) {
				
				int op = 0;
				
				System.out.println("\nPoredak: ");
				System.out.println("  [1] rastuci");
				System.out.println("  [2] opadajuci");
				
				do {
										
					System.out.print("Izbor : ");
					op = sc.nextInt();
					
					if (op != 1 && op != 2) System.out.println("Nepostojeca komanda.");
					
				} while (op != 1 && op != 2);
				
				if (op == 2) op2 = true;
			}
			
			switch (op1) {
			
				case 1: 
					Arrays.sort(g.studenti());
					System.out.println();
					g.stampaj();
					break;
				case 2: 
					sortirajNiz(g.studenti(), new KompozitniKomparator(
							new KomparatorPoGodini(), 
							new KomparatorPoPrezimenu(), op2));
					System.out.println();
					g.stampaj();
					break;
				case 3:
					sortirajNiz(g.studenti(), new KomparatorPoDuziniImena());
					System.out.println();
					g.stampaj();
					break;
				default:
					break;
			}
			
		} while (op1 != 4);
		
		sc.close();
	}

	public static void main(String[] args) throws IOException {
		
		String imeFajla;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Fajl za ucitavanje: ");
		imeFajla = sc.next();
		
		Generacija g = Generacija.ucitaj("res//" + imeFajla + ".txt");
		
		sortMenu(g);
		
		System.out.print("\nFajl za cuvanje: ");
		imeFajla = sc.next();
		
		g.sacuvaj("res//" + imeFajla + ".txt");
		
		System.out.println("Kraj rada.");
		
		sc.close();
	}
}

class KompozitniKomparator implements Comparator<Student> {
	
	private final Comparator<Student> prim;
	private final Comparator<Student> sknd;
	
	boolean rev;
	
	public KompozitniKomparator(Comparator<Student> prim, Comparator<Student> sknd, boolean rev) {
		this.prim = prim;
		this.sknd = sknd;
		this.rev = rev;
	}

	@Override
	public int compare(Student s1, Student s2) {
		
		return !rev ? prim.compare(s1, s2) != 0 ?
					  prim.compare(s1, s2) : sknd.compare(s1, s2) :
						  
					  prim.compare(s2, s1) != 0 ?
					  prim.compare(s2, s1) : sknd.compare(s2, s1) ;
	}
}

class KomparatorPoGodini implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) { return s1.godina() - s2.godina(); }
}

class KomparatorPoPrezimenu implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) { return s1.prezime().compareTo(s2.prezime()); }
}

class KomparatorPoDuziniImena implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) { 
		return (s1.ime().length() + s1.prezime().length() - s2.ime().length() - s2.prezime().length()) * (-1);
	}
}