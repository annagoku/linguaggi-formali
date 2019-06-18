/**
 * Esercizio 1.10 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio10Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Esercizio 1.10");
		test("/*/");
		test("/**/***/");
		test("/****/");
		test("/*a*a*/");
		test("/*a/**/");
		test("/**a///a/a**/");
		test("/**/");
		test("/*/*/");
	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio10Commenti.main(new String[] {s});
	}
}
