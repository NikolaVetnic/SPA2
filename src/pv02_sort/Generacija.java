package pv02_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Generacija {
	
	private Student[] studenti;
	
	private Generacija(String imeFajla) throws IOException {
		
		BufferedReader br = new BufferedReader(
				new FileReader(imeFajla));
		
		studenti = new Student[Integer.parseInt(br.readLine())];
		
		for (int i = 0; i < studenti.length; i++)
			studenti[i] = Student.novi(
					br.readLine(), 
					br.readLine(), 
					Integer.parseInt(br.readLine()));
		
		br.close();
	}

	public static Generacija ucitaj(String imeFajla) throws IOException {
		return new Generacija(imeFajla);
	}
	
	public void sacuvaj(String imeFajla) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(imeFajla));
		
		bw.write(this.studenti.length + "\n");
		
		for (Student s : studenti) {
			
			bw.write(s.prezime() + "\n");
			bw.write(s.ime() + "\n");
			bw.write(s.godina() + "\n");
		}
		
		bw.close();
	}
	
	public void stampaj() {
		
		for (Student s : studenti)
			System.out.println(s);
	}

	public Student[] studenti() { return studenti; }
}
