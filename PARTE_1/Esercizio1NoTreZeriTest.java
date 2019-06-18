/**
 * Esercizio 1.1 - Classe di test per DFA NoTreZeri
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio1NoTreZeriTest {

	public static void main(String[] args) {
		System.out.println("TEST no tre zeri consecutivi");
		test("010101");
		test("1100011001");
		test("10214");
		test("100110011011001");
		test("1001100011011001");
	}

	static void test(String s) {
		System.out.println("Test input -> " + s);

		Esercizio1NoTreZeri.main(new String[] { s });

	}
}
