public class Token {
    public final int tag;
    public String lexeme="";
    
    public Token(int t) {  /*costruttore di classe _ prende un simbolo di punteggiatura in ingresso e 
    inizializza la variabile di istanza tag al valore t passato*/
        tag = t;
        lexeme=null;
        }
        
    public Token(int t, String s) {	/*questo costruttore viene chiamato solo dal costruttore della classe Number ed inizializza i campi di Token*/
		tag = t;
		lexeme = s;
    }
        
    public String toString() {return "<" + tag + ">";}
    
    public static final Token
	
	lpt = new Token('('),
	rpt = new Token(')'),
	plus = new Token('+'),
	minus = new Token('-'),
	mult = new Token('*'),
	div = new Token('/');

    
    /* crea nuove istanze della classe token_ la punteggiatura è trattata come istanza di classe e trasformata 
    in sigle alfabetiche*/
}