import java.io.*; 
import java.util.*;

public class Lexer {

    private int line = 1;   /*variabili di istanza*/
    private char peek = ' ';
    Hashtable<String,Word> words = new Hashtable<String,Word>(); /* da approfondire*/
    
    void reserve(Word w) { 	/* metodo che riserva parole chiave all'interno dell' HASTABLE*/
		words.put(w.lexeme, w);
	}
    
    public Lexer() {		      /*costruttore di Lexer: inserisce le PAROLE CHIAVE tramite la reserve nella HASHTABLE */
		reserve(Word.read);
		reserve(Word.casetok);
		reserve(Word.when);
		reserve(Word.then);
		reserve(Word.whiletok);
		reserve(Word.dotok);
		reserve(Word.elsetok);
		reserve(Word.print);
	}
    
    
    private void readch(BufferedReader br) {  /*metodo di istanza_classe standard di Java*/
        try {
            peek = (char) br.read();  /*read() è un metodo della classe buffered reader che legge un carattere per volta*/
        } catch (IOException exc) {
            peek = (char) -1; // ERROR /* errore dovuto a buffer vuoto o inesistente*/
        }
    }

    public int getLine() {
    	return line;
    }
    
    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br); /*?*/
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;
            case '(':               /*gestione di tutte le altre istanze della classe Token*/ 
                peek= ' ';
                return Token.lpt;
            case ')':
                peek= ' ';
                return Token.rpt;
            case '{':
                peek= ' ';
                return Token.lpg;
            case '}':
                peek= ' ';
                return Token.rpg;
            case '+':
                peek= ' ';
                return Token.plus;
            case '-':
                peek= ' ';
                return Token.minus;
            case '*':
                peek= ' ';
                return Token.mult;
            case '/':
                peek= ' ';
                return Token.div;
            case ';':
                peek= ' ';
                return Token.semicolon;
                
	// ... gestire i casi di (, ), {, }, +, -, *, /, ; ... //

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':                   /*gestione di tutte le altre istanze della classe Word*/
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : "  + peek );
                    return null;
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                } else {
                    System.err.println("Erroneous character"
                            + " after = : "  + peek );
                    return null;
                }
            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } 
                else if (peek=='>'){
                    peek=' ';
                    return Word.ne;
                }
                else  {
                    return Word.lt;
                }
                
            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else  {
                    return Word.gt;
                }
            case ':':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.assign;
                } else {
                    System.err.println("Erroneous character"
                            + " after : : "  + peek );
                    return null;
                }
               
	// ... gestire i casi di ||, <, >, <=, >=, ==, <>, := ... //
          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {
                    String s = "";
					do {
						s+= peek;
						readch(br);
					} while (Character.isDigit(peek) || Character.isLetter(peek));		/*continuo a comporre la stringa s finche trovo una lettera o un numero */
					
					if ((Word)words.get(s) != null){		/*ho finito di leggere la stringa e controllo se esiste una parola riservata nella HASHTABLE */
						return(Word)words.get(s);
                    }
					else {		/* altrimenti ho trovato un nuovo identificatore */
						Word wID = new Word(Tag.ID, s);
						words.put(s, wID);			/* metto il nuovo identificatore nella HASHTABLE */
						return wID;
                    }
                    
	// ... gestire il caso degli identificatori e delle parole chiave //

                } else if (Character.isDigit(peek)) { 
                    String s = "";
						do {
						s+= peek;
						readch(br);
						} while (Character.isDigit(peek));		/* continua a comporre s finchè trova numeri*/
					
					NumberTok NUM = new NumberTok(Tag.NUM, s);	/* crea un nuovo oggetto numero e lo ritorna */
					return NUM;

	// ... gestire il caso dei numeri ... //

                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./esempio_semplice.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}