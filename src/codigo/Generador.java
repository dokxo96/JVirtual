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
    public static final int CONDMIENTRAS =4;
    public static final int COND_Y_SI = 5;
    public static final int Imprime = 6;
    public static final int SENAV = 7;
    public static final int SENAA = 8;
    
    
    
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
    public static void esc(int operacion, String cond, String arg1, String operando, String arg2 ){
            switch(operacion){
                case CONDMIENTRAS:
                    Vista.txaResultado.append(cond+"("+arg1+" "+operando+" "+arg2+")"+"{"+" "+"}"+"\n");
                    break;
            }
    }
    public static void mensaje (int operacion, String arg1, String arg2){
        switch(operacion){
            case Imprime:
                Vista.txaResultado.append(arg1+" "+arg2);
                break;
        }       
    }
    public static void sen(int operacion, String arg1, String arg2){
        switch(operacion){
            case SENAV:
                Vista.txaResultado.append(arg1+" "+arg2);
                break;
            case SENAA:
                Vista.txaResultado.append(arg1+" "+arg2);
                break;
        }
    
    }
}
//Vista.txaResultado.append(arg1+" "+operando+" "+arg2+"\n");