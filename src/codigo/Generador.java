/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;
import codigo.Vista;
/**
 *
 * @author davri
 */
public class Generador {
    public static final int DECLARACION=1;
    public static final int ASIGNACION = 2;
    public static final int DeclaracionBool = 3;
    
    
    public static void gc(int operacion, String arg1, String arg2, String resultado){
        switch(operacion){
            case DECLARACION:
                Vista.txaResultado.append(arg1+" "+resultado+"\n");
                break;
            
            case ASIGNACION:
                Vista.txaResultado.append(arg1+" "+arg2+" "+resultado+"\n");
                break;    
        }
    }
    
    
    public static void bool(int operacion, String arg1, String operando, String arg2){
        switch(operacion){
            case DeclaracionBool:
                Vista.txaResultado.append(arg1+" "+operando+" "+arg2+"\n");
                break;
        }
    }
    
    
}


//Vista.txaResultado.append(arg1+" "+operando+" "+arg2+"\n");