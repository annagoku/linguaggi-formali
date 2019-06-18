public class Token {
    public final int tag;
    public String lexeme;
    public Token(int t) {  
    	/*costruttore di classe _ prende un simbolo di punteggiatura in ingresso e 
    		inizializza la variabile di istanza tag al valore t passato*/
        tag = t;  
    }
    
    public Token(int t, String s){
        tag=t;
        lexeme=s;
    }
        
    public String toString() {
    	return "<" + tag + ">";
    }
    public static final Token
							not = new Token('!'),
							lpt = new Token('('),
							rpt = new Token(')'),
							lpg = new Token('{'),
							rpg = new Token('}'),
							plus = new Token('+'),
							minus = new Token('-'),
							mult = new Token('*'),
							div = new Token('/'),
							semicolon = new Token(';');
    
    /* crea nuove istanze della classe token_ la punteggiatura è trattata come istanza di classe e trasformata 
    in sigle alfabetiche*/
}