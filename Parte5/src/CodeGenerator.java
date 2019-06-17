import java.util.LinkedList;
import java.io.*;

public class CodeGenerator {  /*Genera una lista di oggetti di tipo istruction*/

    LinkedList <Instruction> instructions = new LinkedList <Instruction>(); /*<istruction> specifica il tipo di oggetti
                                                                              collezionati nella linkedList*/

    int label=0;

    public void emit(OpCode opCode) {
        instructions.add(new Instruction(opCode));
    }

    
    public void emit(OpCode opCode , int operand) {    /*agginge istruzioni*/ 
        instructions.add(new Instruction(opCode, operand));
    }

    public void emitLabel(int operand) { /*aggiunge etichette di salto*/
        emit(OpCode.label, operand);
    }

    public int newLabel() {
        return label++;
    }

    public void toJasmin() throws IOException{
        PrintWriter out = new PrintWriter(new FileWriter("Output.j")); /*classe che stampa rappresentazioni formattate di oggetti
                                                                       in un testo*//*classe per scrivere file di caratteri 
                                                                       il suo costruttore prende in ingresso il nome del file
                                                                        sotto forma di stringa*/
        String temp = "";
        temp = temp + header;
        while(instructions.size() > 0){ /* dimensione della linkedlist*/
            Instruction tmp = instructions.remove(); 
            temp = temp + tmp.toJasmin(); /*tutte le istruzioni vengono accumulate in temp*/
        }
        temp = temp + footer;
        out.println(temp);
        out.flush(); /*salva lo stream dal buffer alla destinazione finale*/
        out.close(); /*chiude lo stream*/
        System.out.println("Chiudo Code Generation");
    }

    private static final String header = ".class public Output \n" /*costanti necessarie come preambolo*/
        + ".super java/lang/Object\n"
        + "\n"
        + ".method public <init>()V\n"
        + " aload_0\n"
        + " invokenonvirtual java/lang/Object/<init>()V\n"
        + " return\n"
        + ".end method\n"
        + "\n"
        + ".method public static print(I)V\n"
        + " .limit stack 2\n"
        + " getstatic java/lang/System/out Ljava/io/PrintStream;\n"
        + " iload_0 \n"
        + " invokestatic java/lang/Integer/toString(I)Ljava/lang/String;\n"
        + " invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n"
        + " return\n"
        + ".end method\n"
        + "\n"
        + ".method public static read()I\n"        
        + " .limit stack 3\n"        
        + " new java/util/Scanner\n"        
        + " dup\n"        
        + " getstatic java/lang/System/in Ljava/io/InputStream;\n"        
        + " invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n"        
        + " invokevirtual java/util/Scanner/next()Ljava/lang/String;\n"        
        + " invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I\n"        
        + " ireturn\n"        
        + ".end method\n"
        + "\n"
        + ".method public static run()V\n"
        + " .limit stack 1024\n"
        + " .limit locals 256\n";

    private static final String footer = " return\n" /*costanti necessarie come epilogo*/
        + ".end method\n"
        + "\n"
        + ".method public static main([Ljava/lang/String;)V\n"
        + " invokestatic Output/run()V\n"
        + " return\n"
        + ".end method\n";
}