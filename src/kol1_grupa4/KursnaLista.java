package kol1_grupa4;

public class KursnaLista implements Comparable<KursnaLista> {

	private String ozn;
	private int kup, srd, pro, dan;
	
	public KursnaLista(String ozn, int kup, int srd, int pro, int dan) {
		this.ozn = ozn;
		this.kup = kup;
		this.srd = srd;
		this.pro = pro;
		this.dan = dan;
	}
	
	public String toString() {
		return String.format("%-10s kupovni: %-8d srednji: %-8d prodajni: %-8d dan: %2d",
				ozn, kup, srd, pro, dan);
	}
	
	public String ozn() 	{ return ozn; }
	public int kup() 		{ return kup; }
	public int srd() 		{ return srd; }
	public int pro() 		{ return pro; }
	public int dan() 		{ return dan; }

	@Override
	public int compareTo(KursnaLista that) {
		return kup - that.kup;
	}
}
