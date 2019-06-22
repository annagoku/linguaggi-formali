import java.io.*; 

public class Valutatore {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Valutatore(Lexer l, BufferedReader br) { 
	lex = l; 
	pbr = br;
	move(); 
    }
   
    void move() { 
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
	// come in Esercizio 3.1
    }

    void error(String s) { 
        throw new Error("near line " + lex.line + ": " + s);
	// come in Esercizio 3.1
    }

    void match(int t) {
        if (look.tag == t) {
	    if (look.tag != Tag.EOF) move();
        } else error("syntax error");
	// come in Esercizio 3.1
    }

    public void start() { 
        int expr_val=0;
        if (look.tag=='(' || look.tag==Tag.NUM){
            expr_val=expr();
            match(Tag.EOF);
            System.out.println("\n" + "Risultato: " + expr_val);
            System.out.println("-------------->FINE");
        } 
        else error ("Syntax error in expr");
    }


    private int expr() { 
	int term_val=0, exprp_val=0, exprp_i=0, expr_val=0;
    if (look.tag=='(' || look.tag==Tag.NUM){
        term_val = term(); /*attributi sintetizzati*/
        exprp_val=exprp(term_val);
    }
    else error ("Syntax error in expr");
    return exprp_val;
    }

    private int exprp(int exprp_i) {
        int term_val=0, exprp_val=0;
        if (look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==Tag.EOF){
            switch (look.tag) {
            case '+':
                        match('+');
                        term_val = term();
                        exprp_val = exprp(exprp_i+term_val);
                        break;
            case '-': 
                        match('-');
                        term_val = term();
                        exprp_val = exprp(exprp_i-term_val);
                        break;
            case Tag.EOF:
                        exprp_val=exprp_i;
                        break;
            case ')':
                        exprp_val=exprp_i;
                        break;
            }
        }
        else error ("Syntax error in exprp");
        return exprp_val;
    }

    private int term() { 
        int termp_i=0, fact_val=0, term_val=0, termp_val=0;
        if (look.tag=='(' || look.tag==Tag.NUM){
            fact_val=fact();
            term_val=termp(fact_val);
        }
        else error ("Syntax error in term");
        return term_val;
    }
    
    private int termp(int termp_i) { 
        int fact_val=0, termp_val=0;
        if (look.tag=='*' || look.tag==')' || look.tag=='/' || look.tag=='+' || look.tag=='-' || look.tag==Tag.EOF){
            switch(look.tag){
                case '*':
                    match('*');
                    fact_val=fact();
                    termp_val=termp(termp_i*fact_val);
                    break;
                case '/':
                    match('/');
                    fact_val=fact();
                    termp_val=termp(termp_i/fact_val);
                    break;
                case '+':
                    termp_val=termp_i;
                    break;
                case '-':
                    termp_val=termp_i;
                    break;
                case Tag.EOF:
                    termp_val=termp_i;
                    break;
                case ')':
                    termp_val=termp_i;
                    break;
            }
        }
        else error ("Syntax error in termp");
        return termp_val;
    }
    
    private int fact() { 
        int fact_val=0, expr_val=0;
        if (look.tag=='('|| look.tag==Tag.NUM){
            switch (look.tag){
                case '(':
                    match ('(');
                    fact_val=expr();
                    match (')');
                    break;
                case Tag.NUM:
                    fact_val=Integer.parseInt(look.lexeme);
                    match (Tag.NUM);
                    break;
            }
        }
        else error ("Syntax error in fact");
        return fact_val;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./esempio_op.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore valutatore = new Valutatore(lex, br);
            valutatore.start();
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}