package p_vezba.p05_hash_tables_p01;

public class OHashMap_Primer1 {

	static class Person {
		private String name;
		private int age;
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public String toString() {
			return name + ", " + age;
		}
	}
	
	public static void main(String[] args) {
		
		OHashMap<String, Person> map = new OHashMap<String, Person>(5);
		
		map.insert("1235", new Person("Pera", 24));
		map.insert("2896", new Person("Mika", 21));
		map.insert("3521", new Person("Zika", 23));
		map.insert("3521", new Person("Fica", 26));
		map.insert("2225", new Person("Mica", 22));
		map.insert("9862", new Person("Tika", 28));
		map.print();
		System.out.println("Osoba 1235: " + map.get("1235"));
		map.print();
	}
}
