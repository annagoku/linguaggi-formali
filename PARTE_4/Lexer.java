import java.io.*; 
import java.util.*;

public class Lexer {

    public int line = 1;   /*variabili di istanza*/
    private char peek = ' ';
   
    
    
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
            readch(br); 
        }
        
        switch (peek) {
            case '(':               
                peek= ' ';
                return Token.lpt;
            case ')':
                peek= ' ';
                return Token.rpt;
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
            case (char)-1:
                return new Token(Tag.EOF);

            default:          
                 if (Character.isDigit(peek)) { 
                    String s = "";
						do {
						s+= peek;
						readch(br);
						} while (Character.isDigit(peek));		/* continua a comporre s finchè trova numeri*/
					
					NumberTok NUM = new NumberTok(Tag.NUM, s);	/* crea un nuovo oggetto numero e lo ritorna */
					return NUM;



                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:/Users/annag/Desktop/LFT/LABORATORIO/PARTE_4/esempio_op.txt"; // il percorso del file da leggere
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