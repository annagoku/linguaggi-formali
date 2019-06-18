/**
 * Esercizio 1.4 - classe di test
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio4Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST Matricole java");
		test("654321 Rossi");
		test(" 123456 Bianchi ");
		test("123456Bia nchi");
		test("123456De Gasperi");
		test("123457De Gasperi");
		test("16 77Riss");
		test("1235 Saba Telli");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio4MatricolaCognome.main(new String[] {s});
	}
}
