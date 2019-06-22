/**
 * Esercizio 1.4 - DFA che, partendo da quello elaborato nell’esercizio 1.3, 
 * riconosca anche la combinazione di matricola e cognome separati o preceduti 
 * o seguiti da una sequenza di spazi.
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio4MatricolaCognome {

	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);

			switch (state) {
			case 0:
				if (ch == ' ') {
					state = 0;
				}
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

			case 1:
				if (ch >= 'L' && ch <= 'Z')
					state = 3;
				else if (ch == ' ') {
					state = 5;
				}
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
				else if (ch == ' ') {
					state = 6;
				}
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
				else if (ch == ' ') {
					state = 4;
				}
				else
					state = -1;
				break;

			case 4:
				if (ch == ' ')
					state = 4;
				else if (ch >= 'A' && ch <= 'Z') {
					state = 3;
				}
				else
					state = -1;
				break;

				
			case 5:
				if (ch == ' ')
					state = 5;
				else if (ch >= 'L' && ch <= 'Z') {
					state = 3;
				}
				else
					state = -1;
				break;
	
			case 6:
				if (ch == ' ')
					state = 6;
				else if (ch >= 'A' && ch <= 'K') {
					state = 3;
				}
				else
					state = -1;
				break;

			
			}
			
		}
		return state == 3 || state == 4;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}