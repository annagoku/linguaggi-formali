public class Instruction { /*rappresenta le singole istruzioni del linguaggio mnemonico*/
    OpCode opCode;  /*variabili di istanza*/
    int operand; /*definisce il numero identificativo di un label o un valore numerico*/

    public Instruction(OpCode opCode) {  /*costruttore*/
        this.opCode = opCode;
    }

    public Instruction(OpCode opCode, int operand) { /*costruttore*/
        this.opCode = opCode;
        this.operand = operand;
    }

    public String toJasmin () {  /*parte dall'Opcode e restituisce una stringa dell'istruzione nel formato per l'assembler 
    associando il valore dell'operando eventualmente connesso*/
        String temp="";
        switch (opCode) {
            case ldc : 
                temp = " ldc " + operand + "\n"; 
                break;
            case invokestatic : 
               if( operand == 1)  
                  temp = " invokestatic " + "Output/print(I)V" + "\n";
               else 
                 temp = " invokestatic " + "Output/read()I" + "\n"; 
                 break;
            case iadd : 
                temp = " iadd " + "\n";
                break;
            case imul : 
                temp = " imul " + "\n"; 
                break;
            case idiv : 
                temp = " idiv " + "\n"; 
                break;
            case isub : 
                temp = " isub " + "\n"; 
                break;
            case ineg : 
                temp = " ineg " + "\n"; 
                break;
            case istore : 
                temp = " istore " + operand + "\n"; 
                break;
            case ior : 
                temp = " ior " + "\n"; 
                break;
            case iand : 
                temp = " iand " + "\n"; 
                break;
            case iload : 
                temp = " iload " + operand + "\n"; 
                break;
            case if_icmpeq : 
                temp = " if_icmpeq L" + operand + "\n"; 
                break;
            case if_icmple : 
                temp = " if_icmple L" + operand + "\n"; 
                break;
            case if_icmplt : 
                temp = " if_icmplt L" + operand + "\n"; 
                break;
            case if_icmpne : 
                temp = " if_icmpne L" + operand + "\n"; 
                break;
            case if_icmpge : 
                temp = " if_icmpge L" + operand + "\n"; 
                break;
            case if_icmpgt : 
                temp = " if_icmpgt L" + operand + "\n"; 
                break;
            case ifne : 
                temp = " ifne L" + operand + "\n"; 
                break;
            case GOto : 
                temp = " goto L" + operand + "\n" ; 
                break;
            case label : 
                temp = "L" + operand + ":\n"; 
                break;
        }
    return temp;
    }
}