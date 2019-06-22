/**
 * Esercizio 1.6 - DFA che riconosce il linguaggio dei numeri 
 * binari il cui valore � multiplo di 3 
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio6BinariTre {
	
	
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);

			switch (state) {
			case 0:
				if (ch == '0')
					state = 0;
				else if (ch == '1')
					state = 1;
				else
					state = -1;
				break;

			case 1:
				if (ch == '0')
					state = 2;
				else if (ch == '1')
					state = 0;
				else
					state = -1;
				break;

			case 2:
				if (ch == '0')
					state = 1;
				else if (ch == '1')
					state = 2;
				else
					state = -1;
				break;

			
			}
		}
		return state == 0;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}