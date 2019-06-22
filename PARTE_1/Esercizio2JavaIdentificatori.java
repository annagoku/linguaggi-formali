/**
 * Esercizio 1.2 - DFA per riconoscere il linguaggio degli identificatori Java
 * @author Annalisa Sabatelli - matr. 866879
 *
 */
public class Esercizio2JavaIdentificatori {
	
	
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;

		while (state >= 0 && i < s.length()) {
			final char ch = Character.toUpperCase(s.charAt(i++));

			switch (state) {
			case 0:
				if (ch >= '0' && ch <= '9')
					state = -1;
				else if (ch=='_')
					state = 1;
				else if (ch >= 'A' && ch <= 'Z')
					state = 2;
				else
					state = -1;
				break;

			case 1:
				if (ch == '_')
					state = 1;
				else if (ch >= 'A' && ch <= 'Z')
					state = 2;
				else if (ch >= '0' && ch <= '9')
					state = 2;
				else
					state = -1;
				break;

			case 2:
				if (ch >= 'A' && ch <= 'Z')
					state = 2;
				else if (ch >= '0' && ch <= '9')
					state = 2;
				else if (ch == '_')
					state = 2;
				else 
					state = -1;
				break;
			}
		}
		return state == 2;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}