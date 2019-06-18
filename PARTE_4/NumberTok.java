public class NumberTok extends Token {
	public String lexeme = ""; /*variabile di istanza_l'altra variabile tag non è dichiarata poichè ereditata dalla classe Token*/
    
    
    
    public NumberTok(int tag, String s) {  /*costruttore della classe*/
        super(tag, s); 
        lexeme=s; 
    }
    public String toString() { return "<" + tag + ", " + lexeme + ">"; }
   
}