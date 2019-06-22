/**
 * Esercizio 1.9 - DFA che riconosce il linguaggio delle stringhe che contengono 
 * il proprio nome e tutte le stringhe ottenute dopo la sostituzione di un 
 * carattere del nome con un altro qualsiasi.
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio9NomeVari {

	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = Character.toUpperCase(s.charAt(i++));

			switch (state) {
			case 0:
				if (ch == 'A') {
						state = 1;
				} else
					state = 5;
				break;

			case 1:
				if (ch == 'N') {
					state = 2;
				} else
					state = 6;
				break;

			case 2:
				if (ch == 'N') {
					state = 3;
				} else
					state = 7;
				break;

			case 3:
				state = 4;
				break;
			case 4:
				state = -1;
				break;
			case 5:
				if (ch == 'N') {
					state = 6;
				} else
					state = -1;
				break;
			case 6:
				if (ch == 'N') {
					state = 7;
				} else
					state = -1;
				break;
			case 7:
				if (ch == 'A') {
					state = 4;
				} else
					state = -1;
				break;
			}
		}
		return state == 4;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}