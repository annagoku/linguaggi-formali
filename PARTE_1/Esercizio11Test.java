/**
 * Esercizio 1.11 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio11Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Esercizio 1.11");
		test("aaa/*/aa");
		test("a/**//***a");
		test("aa/*aa");
		test("aaa/****/aa");
		test("aa/*a*a*/");
		test("aaaa");
		test("/****/");
		test("/*aa*/");
		test("*/a");
		test("a/**/***a");
		test("a/**/***/a");
		test("a/**/aa/***/a");
		test("aaa/*aa*a*/aaaa/*aa*/a");
		test("a*///");
		
	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio11Commenti.main(new String[] {s});
	}
}
