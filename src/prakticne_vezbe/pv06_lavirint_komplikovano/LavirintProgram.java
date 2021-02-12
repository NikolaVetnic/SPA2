package prakticne_vezbe.pv06_lavirint_komplikovano;

import org.svetovid.Svetovid;

/**
 * Program za nalazenje puta u lavirintu.
 * 
 * Date su cetiri varijante problema, od jednostavnijih ka slozenijima,
 * radi ilustracije osnovnih koncepata i postepenog uvodjenja novih.
 * 
 * Najjednostavnije je samo nalazenje da li put postoji.
 * 
 * Prosirenje tog resenja nam ispisuje taj nadjeni put.
 * 
 * Treca varijanta nalazi sve puteve i medju njima bira najkraci.
 * 
 * Cetvrta varijanta resava lavirint u kome su rasuti zlatnici na polj-
 * ima i nalazi put na kome se kupi najvise zlatnika.
 */
public class LavirintProgram {

	public static void main(String[] args) {
		
		Svetovid.out.println("Unesite ime fajla: ");
        String fajl = Svetovid.in.readLine();
        if (!Svetovid.testIn(fajl)) {
            System.out.println("Greska: nema fajla!");
            return;
        }
        
        System.out.println("1 - da li postoji put");
        System.out.println("2 - ispis nekog puta (ako postoji)");
        System.out.println("3 - nalazenje najkraceg puta");
        System.out.println("4 - nalazenje najvrednijeg puta");
        System.out.println("Unesite izbor 1-4:");
        int op = Svetovid.in.readInt();

        Lavirint lavirint = new Lavirint(fajl);
        Put put;

        if (lavirint != null) {

            switch (op) {
            case 1:
                if (lavirint.postojiPut(0, 0)) {
                    System.out.println("Postoji put");
                } else {
                    System.out.println("Ne postoji put");
                }
                break;
            case 2:
                lavirint.nadjiPut(0, 0);
                break;
            case 3:
                put = lavirint.najkraciPut(0, 0);
                if (put != null) {
                    put.stampaj();
                } else {
                    System.out.println("Ne postoji put");
                }
                break;
            case 4:
                put = lavirint.najvrednijiPut(0, 0);
                if (put != null) {
                    put.stampaj();
                } else {
                    System.out.println("Ne postoji put");
                }
                break;
            default:
                System.err.println("Uneli ste pogresan izbor");
            }
        }
	}
}
