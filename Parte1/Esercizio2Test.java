/**
 * Esercizio 1.2 - Classe di test
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio2Test {
	
	
	public static void main (String[] args) {
		System.out.println("TEST indicatori java");
		test("__");
		test("0oisudf_");
		test("_102sdfsdfAASDA14");
		test("__102_sdf_sdfAASDA14");
		test("jkl_aslkdj_askjdl__asdjkl809sdf_sflkjdf_");
		test("_102sdfs:;/()dfAASDA14");
		

	}
	
	static void test(String s) {
		System.out.println("Test input -> "+s);
		
		Esercizio2JavaIdentificatori.main(new String[] {s});
	}
}
