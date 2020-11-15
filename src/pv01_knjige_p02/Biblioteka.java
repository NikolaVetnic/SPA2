package pv01_knjige_p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Biblioteka {

	Knjiga[] knjige;
	
	public Biblioteka(String input) throws IOException {
		
		String file = "res//" + input + ".txt";
		
		BufferedReader br = new BufferedReader(
				new FileReader(file));
		
		knjige = new Knjiga[Integer.parseInt(br.readLine().trim())];
		
		for (int i = 0; i < knjige.length; i++)
			knjige[i] = new Knjiga(
					Integer.parseInt(br.readLine().trim()),
									 br.readLine().trim() ,
									 br.readLine().trim());
		
		br.close();
	}
	
	public void stampaj() {
		
		for (Knjiga k : knjige)
			System.out.println(k);
	}
}
