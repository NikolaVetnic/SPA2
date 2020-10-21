package pv01_studenti;

import java.util.Arrays;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidWriter;

public class Main {

	public static void main(String[] args) {
		
		String fajl;
		
		fajl = Svetovid.in.readLine("Unesite ime fajla: ");
		fajl = "res//" + fajl + ".txt";
		Student[] studenti = ucitajStudente(fajl);
		
		stampajNiz(studenti);
		System.out.println();
		
		Arrays.sort(studenti);
		
		stampajNiz(studenti);
		System.out.println();
		
		fajl = Svetovid.in.readLine("Unesite ime za cuvanje fajla: ");
		fajl = "res//" + fajl + ".txt";
		snimiStudente(studenti, fajl);
	}
	
	public static Student[] ucitajStudente(String fajl) {
		
		if (!Svetovid.testIn(fajl)) {
			
			System.out.println("Fajl ne postoji.");
			return null;
		}
		
		int n = Svetovid.in(fajl).readInt();
		
		Student[] studenti = new Student[n];
		
		for (int i = 0; i < n; i++) {
			
			String prezime = Svetovid.in(fajl).readLine();
			String ime = Svetovid.in(fajl).readLine();
			int god = Svetovid.in(fajl).readInt();
			
			studenti[i] = new Student(prezime, ime, god);
		}
		
		Svetovid.closeIn(fajl);
		
		return studenti;
	}
	
	public static void snimiStudente(Student[] niz, String fajl) {
		
		if (!Svetovid.testOut(fajl)) {
			
			System.out.println("Nemoguce je kreirati fajl.");
			return;
		}
		
		SvetovidWriter out = Svetovid.out(fajl);
		
		out.println(niz.length);
		
		for (int i = 0; i < niz.length; i++) {
			
			out.println(niz[i].getPrezime());
			out.println(niz[i].getIme());
			out.println(niz[i].getGod());
		}
		
		out.close();
	}
	
	public static void stampajNiz(Student[] niz) {
		
		for (Student s: niz)
			System.out.println(s);
	}
}
