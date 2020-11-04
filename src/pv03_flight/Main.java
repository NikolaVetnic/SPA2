package pv03_flight;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		ZabranaLetenja.zabranjeni = ZabranaLetenja.ucitaj(ZabranaLetenja.ZABRANJENI);
//		ZabranaLetenja.zabranjeni.printStats();
		
		for(Putnik p : ZabranaLetenja.zabranjeni)
			System.out.println(p);
		System.out.println();
		
		ZabranaLetenja.pojedinacnaProvera();
		
		ZabranaLetenja.masovnaProvera2();
	}
}
