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

    public void prog() {
        if (look.tag==Tag.ID || look.tag==Tag.PRINT || look.tag==Tag.READ || look.tag==Tag.CASE || look.tag==Tag.WHILE 
        || look.tag=='{'){
            statlist();
            match(Tag.EOF);
            System.out.println("-------------->FINE");
        }
        else error ("Syntax error");
    }

    private void statlist() {
        if (look.tag==Tag.ID || look.tag==Tag.PRINT || look.tag==Tag.READ || look.tag==Tag.CASE || look.tag==Tag.WHILE 
        || look.tag=='{'){
            stat();
            statlistp();
        }
        else error ("Sintax Error");
    }

    private void statlistp() {
        if (look.tag==';' || look.tag==Tag.EOF || look.tag=='}'){
            switch (look.tag) {
            case ';':
                match(';');
                stat();
                statlistp();
                break;
            case '}':
            	break;
            case Tag.EOF:
                break;
            }
        }
        else error ("Syntax error");
    }
    

    private void stat() {
        if (look.tag==Tag.ID || look.tag==Tag.PRINT || look.tag==Tag.READ || look.tag==Tag.CASE || look.tag==Tag.WHILE 
        || look.tag=='{'){
            switch (look.tag){
            case Tag.ID:
                match(Tag.ID);
                match(Tag.ASSIGN);
                expr();
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match('(');
                expr();
                match(')');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('(');
                match(Tag.ID);
                match(')');
                break;
            case Tag.CASE:
                match(Tag.CASE);
                whenlist();
                match(Tag.ELSE);
                stat();
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                match('(');
                bexpr();
                match(')');
                stat();
                break;
            case '{':
                match('{');
                statlist();
                match('}');
                break;
            }
        }
        else error ("Syntax error");
    }

    private void whenlist() {
        if (look.tag==Tag.WHEN){
            whenitem();
            whenlistp();
        }
        else error ("Syntax error");
    }
    
    private void whenlistp(){
        if (look.tag==Tag.WHEN || look.tag==Tag.ELSE || look.tag==Tag.EOF){
            switch(look.tag){
                case Tag.WHEN:
                    whenitem();
                    whenlistp();
                    break;
                case Tag.ELSE:
                    break;
                case Tag.EOF:
                    break;
            }
        }
        else error ("Syntax error");
    }
    
    private void whenitem(){
        if (look.tag==Tag.WHEN){
            match(Tag.WHEN);
            match('(');
            bexpr();
            match(')');
            stat();
        }
        else error ("Syntax error");
    }
    
    private void bexpr(){
        if(look.tag=='(' || look.tag==Tag.NUM || look.tag==Tag.ID){
            expr();
            match(Tag.RELOP);
            expr();
        }
        else error("Syntax error");
    }
    
    private void expr(){
        if(look.tag=='(' || look.tag==Tag.NUM || look.tag==Tag.ID){
            term();
            exprp();
        }
        else error ("Syntax Error");
    }
    
    private void exprp(){
        if (look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==Tag.RELOP || look.tag==';' || look.tag==Tag.EOF
        || look.tag=='}'|| look.tag==Tag.WHEN || look.tag==Tag.ELSE) {
            switch(look.tag){
            case '+':
                match('+');
                term();
                exprp();
                break;
            case '-':
            match('-');
                term();
                exprp();
                break;
            case ')':
                break;
            case ';':
                break;
            case Tag.RELOP:
                break;
            case Tag.EOF:
                break;
            case '}':
            	break;
            case Tag.WHEN:
            	break;
            case Tag.ELSE:
            	break;
            }
        }
        else error ("Syntax error");
    }
    
    private void term(){
        if (look.tag=='(' || look.tag==Tag.NUM || look.tag==Tag.ID){
            fact();
            termp();
        }
        else error ("Syntax Error");
    }
    
    private void termp(){
        if (look.tag=='*' || look.tag=='/' || look.tag==Tag.EOF || look.tag==Tag.RELOP || 
        		look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==';' || look.tag=='}'
        		|| look.tag==Tag.WHEN || look.tag==Tag.ELSE){
            switch (look.tag){
            case '*':
                match ('*');
                fact();
                termp();
                break;
            case '/':
                match ('/');
                fact();
                termp();
                break;
            case Tag.RELOP:
                break;
            case '+':
                break;
            case '-':
                break;
            case ')':
                break;
            case ';':
                break;
            case Tag.EOF:
                break;
            case '}':
            	break;
            case Tag.WHEN:
            	break;
            case Tag.ELSE:
            	break;
            }
        }
        else error ("Syntax Error");
    }
            
    private void fact() {
        if(look.tag=='(' || look.tag==Tag.NUM || look.tag==Tag.ID){
            switch(look.tag){
            case '(':
                match('(');
                expr();
                match(')');
                break;
            case Tag.NUM:
                match(Tag.NUM);
                break;
            case Tag.ID:
                match(Tag.ID);
                break;
            }
        }
        else error ("Syntax Error");
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        //String path = "./esempio_semplice.txt"; // il percorso del file da leggere
        //String path = "./euclid.txt"; // il percorso del file da leggere
        String path = "./factorial.txt"; // il percorso del file da leggere
        //String path = "./max_tre_num.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}