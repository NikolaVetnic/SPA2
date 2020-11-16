package pv03_flight_p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class SpisakPutnika {

	private HashSet<Putnik> spisak;
	
	public SpisakPutnika(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int brPutnika = Integer.parseInt(br.readLine().trim());
		spisak = new HashSet<Putnik>();
		
		for (int i = 0; i < brPutnika; i++)
			spisak.add(new Putnik(
								 br.readLine().trim(),
								 br.readLine().trim(),
				Integer.parseInt(br.readLine().trim())
			));
		
		br.close();
	}
	
	public void stampaj() {
		
		for (Putnik p : spisak)
			System.out.println(p);
	}
	
	public boolean proveraPoImenu(String prz, String ime, int god, boolean proveritiGodinu) {
		
		for (Putnik p : spisak) {
			
			if (p.prz().equals(prz) && p.ime().equals(ime))
				if (!proveritiGodinu)
					return true;
				else
					if (p.god() == god)
						return true;
			
			if (p.prz().equals(prz))
				System.out.printf("UPOZORENJE: poklapanje prezimena <%s %s>.\n", p.prz(), p.ime());
			if (p.ime().equals(ime))
				System.out.printf("UPOZORENJE: poklapanje imena <%s %s>.\n", p.prz(), p.ime());
		}
		
		return false;
	}
	
	public HashSet<Putnik> spisak() { return spisak; }
}
