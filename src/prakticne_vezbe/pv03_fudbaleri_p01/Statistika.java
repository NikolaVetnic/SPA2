package prakticne_vezbe.pv03_fudbaleri_p01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Statistika {

	public static final String F_2019 = "res//f0.txt";
	public static final String F_2020 = "res//f1.txt";
	
	public static Set<Fudbaler> f2019;
	public static Set<Fudbaler> f2020;
	
	public static Set<Fudbaler> ucitaj(String file) throws NumberFormatException, IOException {
		
		if (!(new File(file)).exists()) {
			System.out.println("Trazeni fajl ne postoji.");
			return null;
		}
		
		Set<Fudbaler> skup = new HashSet<Fudbaler>();
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		int num = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i < num; i++)
			skup.add(Fudbaler.novi(
					br.readLine(),
					br.readLine(),
					Integer.parseInt(br.readLine().trim()),
					br.readLine()
					));
			
		br.close();
		
		return skup;
	}
	
	public static void nastavakIgre() throws NumberFormatException, IOException {
		
		f2019 = ucitaj(F_2019);
		f2020 = ucitaj(F_2020);
		
		f2020.retainAll(f2019);
		
		int c = 0;
		int[] poz = new int[4];
		
		System.out.println("Igraci koji su igrali i tokom 2019 i tokom 2020: \n");
		
		for (Fudbaler f : f2020) {
			System.out.println("[" + c++ + "]\t" + f);
			
			if 		(f.poz().equalsIgnoreCase("golman")) 		poz[0]++;
			else if (f.poz().equalsIgnoreCase("odbrambeni")) 	poz[1]++;
			else if (f.poz().equalsIgnoreCase("sredisnji")) 	poz[2]++;
			else 												poz[3]++;
		}
		
		System.out.println();
		
		System.out.println("Golmana:\t" 	+ poz[0]);
		System.out.println("Odbrambenih:\t" + poz[1]);
		System.out.println("Sredisnjih:\t" 	+ poz[2]);
		System.out.println("Napadaca:\t" 	+ poz[3]);
	}
}
