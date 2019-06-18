/**
 * Esercizio 1.8 - DFA riconosce il linguaggio di stringhe sull’alfabeto {a, b} 
 * tali che “a” occorre almeno una volta nelle ultime tre posizioni della stringa 
 * anche se la stringa è composta da meno di tre simboli.
 * 
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio8Linguaggioab {
	
	
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i <s.length()) {
			final char ch = s.charAt(i++);

			switch (state) {
			case 0:
				if (ch == 'a')
					state = 1;
				else if (ch == 'b')
					state = 0;
				else
					state = -1;
				break;

			case 1:
				if (ch == 'a')
					state = 1;
                else if (ch == 'b')
                    state = 2;
				else
					state = -1;
				break;

			case 2:
				if (ch == 'b')
					state = 3;
				else if (ch == 'a')
					state = 1;
				else
					state = -1;
				break;

			case 3:
				if (ch == 'a')
					state = 1;
                else if (ch == 'b')
                    state = 0;
				else 
					state = -1;
				break;
			}
		}
		return (state == 1 || state==2 || state==3);
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}