import java.util.*;

public class SymbolTable { /*tabella che tiene traccia degli identificatori*/

     Map <String, Integer> OffsetMap = new HashMap <String,Integer>(); 
     /*interface Map specifica il tipo della chiave  e il tipo del valore*/                                                                       
     /*HashMap è una classe che implementa Map*/  
     
	public void insert( String s, int address ) {
            if( !OffsetMap.containsValue(address) ) /*controlla che il valore specificato non sia presente
                                                     ossia non corrisponda ad alcuna chiave*/
                OffsetMap.put(s,address);  /*se non presente l'aggiunge specificando Key, value*/
            else 
                throw new IllegalArgumentException("Reference to a memory location already occupied by another variable");
	}

	public int lookupAddress ( String s ) {
            if( OffsetMap.containsKey(s) ) /*controlla che ci sia la chiave*/
                return OffsetMap.get(s);   /*ritorna il valore associato alla chiave*/
            else 
                return -1; /*se la chiave non è presente*/
	}
}
