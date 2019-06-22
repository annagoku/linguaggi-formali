public enum OpCode { 
    ldc, imul, ineg, idiv, iadd, 
    isub, istore, ior, iand, iload,
    if_icmpeq, if_icmple, if_icmplt, if_icmpne, if_icmpge, 
    if_icmpgt, ifne, GOto, invokestatic, label }
    
    /*enum è uno speciale tipo che vincola una variabile ad assumere solo i valori enumerati*/