/**
 * Esercizio 1.9 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio9Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Esercizio 1.9");
		test("ANNA");
		test("%NNA");
		test("LAURA");
		test("ALNA");
		test("ALIA");
		test("ANNA7");
	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio9NomeVari.main(new String[] {s});
	}
}
