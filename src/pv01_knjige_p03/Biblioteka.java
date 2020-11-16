package pv01_knjige_p03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Biblioteka {

	Knjiga[] knjige;
	
	public Biblioteka(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		knjige = new Knjiga[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < knjige.length; i++)
			knjige[i] = new Knjiga(
					Integer.parseInt(br.readLine().trim()),
									 br.readLine().trim() ,
									 br.readLine().trim());
		
		br.close();
	}
	
	public void snimi(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		bw.write(knjige.length + "\n");
		
		for (Knjiga k : knjige) {
			bw.write(k.id() + "\n");
			bw.write(k.naslov() + "\n");
			bw.write(k.pisac() + "\n");
		}
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (Knjiga k : knjige)
			System.out.println(k);
	}
}
