/**
 * Esercizio 1.8 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio8Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Linguaggio a b (ultime 3 posizioni");
		test("abb");
		test("baaaaaaa");
		test("abbbbbb");
		test("b");
		test("bbabbbbbbbb");
		test("bababababababababbb");
		test("bababababababababba");
	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio8Linguaggioab.main(new String[] {s});
	}
}
