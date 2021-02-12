package prakticne_vezbe.pv03_fudbaleri_p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StatistikaHashSet {
	
	/**
	 * Za isti zadatak ovde koristim HashSet umesto PMF custom klase S-
	 * tatSet - takodje funkcionise, nejasno zasto je neophodno korist-
	 * iti StatSet i da li je neophodno uopste.
	 */

	public static HashSet <Fudbaler> ucitaj(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		int num = Integer.parseInt(br.readLine().trim());
		HashSet <Fudbaler> skup = new HashSet<Fudbaler>();
		
		for (int i = 0; i < num; i++)
			skup.add(new Fudbaler(
					br.readLine().trim(),
					br.readLine().trim(),
					br.readLine().trim(),
					br.readLine().trim())
					);
		
		br.close();
		
		return skup;
	}
	
	public static void main(String[] args) throws IOException {
		
		HashSet <Fudbaler> f2019 = ucitaj("f0");
		HashSet <Fudbaler> f2020 = ucitaj("f1");
		
		f2020.retainAll(f2019);
		
		int count = 0;
		int[] poz = new int[4];
		
		System.out.println("Fudbaleri iz 2019 koji igraju u 2020: ");
		
		for (Fudbaler f: f2020) {
			System.out.println(count++ + "\t" + f);
			
			if		(f.poz().equals("golman")) 		poz[0]++;
			else if (f.poz().equals("napadac")) 	poz[1]++;
			else if (f.poz().equals("sredisnji"))	poz[2]++;
			else									poz[3]++;
		}
		
		System.out.println();
		System.out.println("Golmana:\t" 	+ poz[0]);
		System.out.println("Napadaca:\t" 	+ poz[1]);
		System.out.println("Sredisnjih:\t" 	+ poz[2]);
		System.out.println("Odbrambeni:\t" 	+ poz[3]);
	}
}
