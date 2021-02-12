package prakticne_vezbe.pv03_fudbaleri_p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Statistika {

	public static StatSet <Fudbaler> ucitaj(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		int num = Integer.parseInt(br.readLine().trim());
		StatSet<Fudbaler> skup = new StatSet<Fudbaler>();
		
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
		
		/**
		 * Koriscenje StatSet klase u principu ima isti efekat u ovom sluÄ?-
		 * aju kao kada bismo koristili HashSet, meÄ‘utim objekat klase Sta-
		 * tSet dozvoljava pozivanje dodatne statistike printStats() metod-
		 * om koji se poziva nad instancom klase.
		 */
		
		StatSet <Fudbaler> f2019 = ucitaj("f0");
		StatSet <Fudbaler> f2020 = ucitaj("f1");
		
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
