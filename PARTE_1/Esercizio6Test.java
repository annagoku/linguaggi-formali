/**
 * Esercizio 1.6 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio6Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Binari divisibili per 3");
		test("110");
		test("1001");
		test("10");
		test("111");
		test("1245");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio6BinariTre.main(new String[] {s});
	}
}
