/**
 * Esercizio 1.7 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio7Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Linguaggio a b ");
		test("ababbbb");
		test("ba");
		test("bba");
		test("bbbababababab");
		test("b");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio7Linguaggioab.main(new String[] {s});
	}
}
