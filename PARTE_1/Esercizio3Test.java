/**
 * Esercizio 1.3 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio3Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Matricole java");
		test("0123");
		test("Bianchi");
		test("987Rossi");
		test("123B");
		test("122B");
		test("1678riss");
		test("1235SabaTelli");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio3MatricolaCognome.main(new String[] {s});
	}
}
