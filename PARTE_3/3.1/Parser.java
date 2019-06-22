import java.io.*;

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
    	throw new Error("near line " + lex.getLine() + ": " + s);
    }

    void match(int t) {
	if (look.tag == t) {
	    if (look.tag != Tag.EOF) move();
	} else error("syntax error");
    }

    public void start() {
        // ... completare ...
        if (look.tag=='(' || look.tag==Tag.NUM){
            expr();
            match(Tag.EOF);
            // ... completare ...
            System.out.println("-------------->FINE");
            }
        
        else error ("sintax error");
        }

    private void expr() {
	// ... completare ...
        if (look.tag=='(' || look.tag==Tag.NUM){
            term();
            exprp();
        }
        else error ("syntax error");
    }

    private void exprp() {
        if (look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==Tag.EOF){
            switch (look.tag) {
            case '+':
            // ... completare ...
                match('+');
                term();
                exprp();
                break;
            case '-':
                match('-');
                term();
                exprp();
                break;
            case Tag.EOF:
                break;  //do nothing//
            case ')':
                break;
            }
        }
        else error ("syntax error");
    }

    private void term() {
        // ... completare ...
        if (look.tag=='(' || look.tag==Tag.NUM){
            fact();
            termp();
        }
        else error ("syntax error");
    }

    private void termp() {
        // ... completare ...
        if (look.tag=='*' || look.tag==')' || look.tag=='/' || look.tag=='+' || look.tag=='-' || look.tag==Tag.EOF){
            switch(look.tag){
            case '*':
                match('*');
                fact();
                termp();
                break;
            case '/':
                match('/');
                fact();
                termp();
                break;
            case '+':  //do nothing
                break;
            case '-':
                break;
            case ')':
                break;
            case Tag.EOF:
                break;
            }
        }
        else error ("syntax error");
    }

    private void fact() {
        // ... completare ...
        if (look.tag=='('|| look.tag==Tag.NUM){
            switch (look.tag){
            case '(':
                match ('(');
                expr();
                match (')');
                
                break;
            case Tag.NUM :
                match (look.tag);
                break;
            }
        }
        else error ("Syntax error");
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./esempio_op.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}