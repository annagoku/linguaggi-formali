/**
 * Esercizio 1.5 - DFA che, partendo da quello elaborato nell’esercizio 1.3, riconosca 
 * il linguaggio di stringhe di matricole e cognomi degli studenti del turno 2 e 3 di 
 * laboratorio in cui il cognome precede il numero di matricola. 
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio5MatricolaCognome {

	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			final char c = Character.toUpperCase(ch);

			switch (state) {
			case 0:
				if (ch >= 'L' && ch <= 'Z') {
					state = 1;
					
				}
				else if (ch >= 'A' && ch <= 'K') {
					state = 2;
				}
				else
					state = -1;
				break;

			case 1:

				if (c >= 'A' && c <= 'Z')
					state = 1;
				else if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 4;
					} else {
						state = 3;
					}
				} else
					state = -1;
				break;

			case 2:
				if (c >= 'A' && c <= 'Z')
					state = 2;
				else if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 5;
					} else {
						state = 6;
					}
				}

				else
					state = -1;
				break;

			case 3:
				if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 4;
					} else {
						state = 3;
					}
				}
				else
					state = -1;
				break;

				
			case 4:
				if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 4;
					} else {
						state = 3;
					}
				}
				else
					state = -1;
				break;

			case 5:
				if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 5;
					} else {
						state = 6;
					}
				}
				else
					state = -1;
				break;

				
			case 6:
				if (ch >= '0' && ch <= '9') {
					int num = (int) ch;
					if (num % 2 == 0) {
						state = 5;
					} else {
						state = 6;
					}
				}
				else
					state = -1;
				break;
	
			}
		}
		return state == 3 || state == 5;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}