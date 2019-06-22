import java.io.*;

public class Translator {
	private Lexer lex;
	private BufferedReader pbr;
	private Token look;

	SymbolTable st = new SymbolTable();
	CodeGenerator code = new CodeGenerator();
	int count = 0;

	public Translator(Lexer l, BufferedReader br) {
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
		throw new Error("near line " + lex.getLine() + ": " + s);
		// come in Esercizio 3.1
	}

	void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			error("syntax error");
		// come in Esercizio 3.1
	}

	public void prog() { 
		if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.READ || look.tag == Tag.CASE
				|| look.tag == Tag.WHILE || look.tag == '{') {

			int lnext_statlist = code.newLabel();
			statlist(lnext_statlist);
			code.emitLabel(lnext_statlist);
			match(Tag.EOF);
			try {
				code.toJasmin();
			} catch (java.io.IOException e) {
				System.out.println("IO error\n");
			}
		} else
			error("Syntax error in grammar (prog) after read( with " + look);
		
	}

	public void statlist(int lnext_statlist) {
		if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.READ || look.tag == Tag.CASE
				|| look.tag == Tag.WHILE || look.tag == '{') {
			int lnext_stat=code.newLabel();
			stat(lnext_stat);
			code.emitLabel(lnext_stat);
			statlistp(lnext_statlist);
		} else
			error("Error in grammar (stat) after read( with " + look);
	}

	public void statlistp(int lnext_statlistp) {
		
		if (look.tag==';' || look.tag==Tag.EOF || look.tag=='}'){
			
			switch (look.tag) {
            case ';':
                match(';');
    			int lnext_stat = code.newLabel();
    			stat(lnext_stat);
    			code.emitLabel(lnext_stat);
    			statlistp(lnext_statlistp);
    			
                break;
            default :
            	break;
            }
		} else
			error("Syntax error in grammar (statlistp) after read( with " + look);
	}

	public void stat(int lnext_stat) {  
		int ltrue_bexpr=0;
		int lfalse_bexpr=0;
		int ltrue_whenlist=0;
		int lfalse_whenlist=0;
		int lnext_stat1 = lnext_stat;
        
		switch(look.tag) {
            case Tag.PRINT:
                match(Tag.PRINT);
                match('(');
                expr();
                code.emit(OpCode.invokestatic,1);
                match(')');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('(');
                if (look.tag==Tag.ID) {
                    int read_id_addr = st.lookupAddress(((Word)look).lexeme);
                    if (read_id_addr==-1) {
                        read_id_addr = count;
                        st.insert(((Word)look).lexeme,count++);
                    }                    
                    match(Tag.ID);
                    match(')');
                    code.emit(OpCode.invokestatic,0);
                    code.emit(OpCode.istore,read_id_addr);   
                }
                else
                    error("Error in grammar (stat) after read( with " + look);
                break;
            case Tag.ID:
                int id_addr = st.lookupAddress(((Word)look).lexeme);
                
                if (id_addr==-1) {
                    id_addr = count;
                    st.insert(((Word)look).lexeme,count++);
                }                    
                match(Tag.ID);
                match(Tag.ASSIGN);
                expr();
                code.emit(OpCode.istore, id_addr);
                break;
            case '{':
                match('{');
                statlist(lnext_stat);
                match('}');
                break;
                
            case Tag.WHILE:
                match(Tag.WHILE);
                match ('(');
            
                ltrue_bexpr=code.newLabel();
             
                lfalse_bexpr=lnext_stat;
                lnext_stat1=code.newLabel();
                code.emitLabel(lnext_stat1);
                bexpr (ltrue_bexpr, lfalse_bexpr);
                match(')');
                code.emitLabel(ltrue_bexpr);
                stat(lnext_stat1);
                
                code.emit(OpCode.GOto, lnext_stat1);
                
                break;
                
            case Tag.CASE:
                match(Tag.CASE);
                ltrue_whenlist = code.newLabel();
                lfalse_whenlist = code.newLabel();
                lnext_stat1=lnext_stat;
                
                whenlist (ltrue_whenlist, lfalse_whenlist);
                code.emit(OpCode.GOto, lnext_stat);
                
                match(Tag.ELSE);
                
                
                code.emitLabel(lfalse_whenlist);
                stat(lnext_stat1);
                
                code.emit(OpCode.GOto, lnext_stat);
                code.emitLabel(lnext_stat);
                break; 
        }

	}

	public void whenlist(int ltrue_whenlist, int lfalse_whenlist) {
		if (look.tag == Tag.WHEN) {
			whenitem(ltrue_whenlist, lfalse_whenlist);
			whenlistp(ltrue_whenlist, lfalse_whenlist);
		}
		else
			error("Syntax error in grammar (whenlist) after read( with " + look);
	}

	public void whenlistp(int ltrue_whenlistp, int lfalse_whenlistp) {
		if (look.tag == Tag.WHEN || look.tag == Tag.ELSE || look.tag == Tag.EOF) {

			if(look.tag == Tag.WHEN) {
				
				
				whenitem(ltrue_whenlistp, lfalse_whenlistp);
				whenlistp(ltrue_whenlistp, lfalse_whenlistp);
			}
			
		} else
			error("Syntax n grammar (whenlistp) after read( with " + look);
	}

	public void whenitem(int ltrue_whenitem, int lfalse_whenitem) {
		if (look.tag == Tag.WHEN) {
			match(Tag.WHEN);
			match('(');
			bexpr(ltrue_whenitem, lfalse_whenitem);
			code.emitLabel(ltrue_whenitem);
			match(')');
			stat(ltrue_whenitem);
		} else
			error("Syntax n grammar (whenitem) after read( with " + look);
	}

	public void bexpr(int ltrue_bexpr, int lfalse_bexpr) {
		expr();
		if (look == Word.eq) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmpeq, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else if (look == Word.lt) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmplt, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else if (look == Word.gt) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmpgt, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else if (look == Word.le) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmple, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else if (look == Word.ne) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmpne, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else if (look == Word.ge) {
			match(Tag.RELOP);
			expr();
			code.emit(OpCode.if_icmpge, ltrue_bexpr);
			code.emit(OpCode.GOto, lfalse_bexpr);
		} else
			error("Errore in bexpr");
	}

	public void expr() {
		if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
			term();
			exprp();
		}
	}

	public void exprp() {
		if (look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==Tag.RELOP || look.tag==';' || look.tag==Tag.EOF
		        || look.tag=='}'|| look.tag==Tag.WHEN || look.tag==Tag.ELSE) {
			switch (look.tag) {
			case '+':
				match('+');
				term();
				code.emit(OpCode.iadd);
				exprp();
				break;
			case '-':
				match('-');
				term();
				code.emit(OpCode.isub);
				exprp();
				break;
			default:
				break;
			}
		} else
			error("Errore in exprp");
	}

	public void term() {
		if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
			fact();
			termp();
		} else
			error("Errore in term");
	}

	public void termp() {
		if (look.tag=='*' || look.tag=='/' || look.tag==Tag.EOF || look.tag==Tag.RELOP || 
        		look.tag=='+' || look.tag=='-' || look.tag==')' || look.tag==';' || look.tag=='}'
        		|| look.tag==Tag.WHEN || look.tag==Tag.ELSE){
			switch (look.tag) {
			case '*':
				match('*');
				fact();
				code.emit(OpCode.imul);
				termp();
				break;
			case '/':
				match('/');
				fact();
				code.emit(OpCode.idiv);
				termp();
				break;
			default:
				break;
			}
		} else
			error("Errore in termp");
	}

	public void fact() {
		if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
			switch (look.tag) {
			case '(':
				match('(');
				expr();
				match(')');
				break;
			case Tag.NUM:
				code.emit(OpCode.ldc, Integer.parseInt(look.lexeme));
				match(Tag.NUM);
				break;
			case Tag.ID:
				int id_addr = st.lookupAddress(((Word) look).lexeme);
				code.emit(OpCode.iload, id_addr);
				if (id_addr == -1) {
					id_addr = count;
					st.insert(((Word) look).lexeme, count++);
				}
				match(Tag.ID);
				break;
			}
		} else
			error("Errore in fact");
	}

	public static void main(String[] args) {
		Lexer lex = new Lexer(); // creo un oggetto Lexer
		//String path = "./esempio_semplice.pas"; 
		//String path = "./esempio1.pas";
		//String path = "./esempio2.pas"; 
		String path = "./esempio3.pas"; 
																								
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			Translator traduttore = new Translator(lex, br); // creo un oggetto Translator
			traduttore.prog(); // comincia la traduzione prog();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try { // gestione dell'errore
			System.out.println("Lancio Jasmin");
			jasmin.Main.main(new String[]{"Output.j"});
		} catch (Exception err) {
			
			System.out.println("errore ToJasmine");
			err.printStackTrace(System.err);
		}
		System.out.println("\n" + "-------------->FINE");

	}
}