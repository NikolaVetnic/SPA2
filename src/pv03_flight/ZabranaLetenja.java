package pv03_flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ZabranaLetenja {

	public static final String ZABRANJENI = "res//noflightlist.txt";
	public static final String PUTNICI = "res//flight.txt";
	
//	public static Set<Putnik> zabranjeni;
//	public static Set<Putnik> putnici;
	
	public static StatSet<Putnik> zabranjeni;
	public static StatSet<Putnik> putnici;
	
	public static StatSet<Putnik> ucitaj(String file) throws NumberFormatException, IOException {
		
		if (!(new File(file)).exists()) {
			System.out.println("Trazeni fajl ne postoji.");
			return null;
		}
		
//		Set<Putnik> skup = new HashSet<Putnik>();
		StatSet<Putnik> skup = new StatSet<Putnik>();
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		int brPutnika = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i < brPutnika; i++)
			skup.add(
					Putnik.novi(
							br.readLine().trim(),
							br.readLine().trim(),
							Integer.parseInt(br.readLine().trim())
					));
		
		br.close();
		
		return skup;
	}
	
	public static void pojedinacnaProvera() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Unesite ime za pretragu: ");
		String ime = sc.nextLine();
		
		System.out.print("Unesite prezime za pretragu: ");
		String prezime = sc.nextLine();
		
		System.out.print("Unesite godinu za pretragu: ");
		int godina = Integer.parseInt(sc.next());
		
		if (!zabranjeni.contains(Putnik.novi(ime, prezime, godina)))
			System.out.println("Putnik NE SME da leti.");
		else
			System.out.println("Putnik sme da leti.");
		
		sc.close();
	}
	
	public static void masovnaProvera1() throws NumberFormatException, IOException {
		
		putnici = ucitaj(PUTNICI);
		zabranjeni = ucitaj(ZABRANJENI);
		
		for (Putnik p : putnici)
			if (!zabranjeni.contains(p))
				System.out.println("Putnik [ " + p + " ] NE SME da leti.");
	}
	

	public static void masovnaProvera2() throws NumberFormatException, IOException {
		
		putnici = ucitaj(PUTNICI);
		zabranjeni = ucitaj(ZABRANJENI);
		
		int c = 0;
		
		putnici.retainAll(zabranjeni);
		
		for (Putnik p : putnici)
			System.out.println("Putnik #" + c++ + " [ " + p + " ] NE SME da leti.");
		
		System.out.println("Broj putnika koji NE SME da leti: " + c);
	}
}
