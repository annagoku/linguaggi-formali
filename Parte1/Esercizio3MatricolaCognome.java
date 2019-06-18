/**
 * Esercizio 1.3 - DFA che riconosce il linguaggio di stringhe che contengono un numero 
 * di matricola seguito immediatamente da un cognome, dove la combinazione di matricola-cognome 
 * corrisponde a studenti del turno 2 o 3 del laboratorio di Linguaggi Formali e Traduttori.
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio3MatricolaCognome {

	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);

			switch (state) {
			case 0:
				if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 2;
					} else {
						state = 1;
					}
				} else
					state = -1;
				break;

			case 1:
				if (ch >= 'L' && ch <= 'Z')
					state = 3;
				else if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 2;
					} else {
						state = 1;
					}
				} else
					state = -1;
				break;

			case 2:
				if (ch >= 'A' && ch <= 'K')
					state = 3;
				else if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 2;
					} else {
						state = 1;
					}
				}

				else
					state = -1;
				break;

			case 3:
				char c = Character.toUpperCase(ch);
				if (c >= 'A' && c <= 'Z')
					state = 3;
				else
					state = -1;
				break;

			}
		}
		return state == 3;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}