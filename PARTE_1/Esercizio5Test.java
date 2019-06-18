/**
 * Esercizio 1.5 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio5Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Matricole java");
		test("Rossi654321");
		test("Bianchi123456");
		test("Sabatelli123456");
		test("Calafato123456");
		test("Calafato 123456");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio5MatricolaCognome.main(new String[] {s});
	}
}
