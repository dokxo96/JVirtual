/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import arbolgrafico.Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author dokxo
 */
public class Notacion_In_Post extends javax.swing.JFrame {
 private Stack<Character> pila = new Stack<Character>();
    
    /**
     * Creates new form Notacion_In_Post
     */
    public Notacion_In_Post() {
        initComponents();
        
       
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtin = new javax.swing.JTextField();
        txtpost = new javax.swing.JTextField();
        txtres = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        parentesis = new javax.swing.JPanel();
        inicia = new javax.swing.JPanel();
        fin = new javax.swing.JPanel();
        exp = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(0, 10, 18));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(69, 90, 100));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Notación Infija -> Postfija");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Expresión Infija:");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Expresión Postfija:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Resultado:");

        txtin.setToolTipText("2+5");
        txtin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtinKeyPressed(evt);
            }
        });

        txtres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtresActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-play-32Blacn.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-play-32Green.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(69, 90, 100));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Evaluaciones"));

        jLabel5.setText("Parentesis correctos");

        jLabel6.setText("Inicia con operando");

        jLabel7.setText("Termina con operando");

        jLabel8.setText("Exprecion alterna");

        javax.swing.GroupLayout parentesisLayout = new javax.swing.GroupLayout(parentesis);
        parentesis.setLayout(parentesisLayout);
        parentesisLayout.setHorizontalGroup(
            parentesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        parentesisLayout.setVerticalGroup(
            parentesisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout iniciaLayout = new javax.swing.GroupLayout(inicia);
        inicia.setLayout(iniciaLayout);
        iniciaLayout.setHorizontalGroup(
            iniciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );
        iniciaLayout.setVerticalGroup(
            iniciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout finLayout = new javax.swing.GroupLayout(fin);
        fin.setLayout(finLayout);
        finLayout.setHorizontalGroup(
            finLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );
        finLayout.setVerticalGroup(
            finLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout expLayout = new javax.swing.GroupLayout(exp);
        exp.setLayout(expLayout);
        expLayout.setHorizontalGroup(
            expLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );
        expLayout.setVerticalGroup(
            expLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(parentesis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inicia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(parentesis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(inicia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        areaTexto.setBackground(new java.awt.Color(102, 102, 102));
        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        areaTexto.setOpaque(true);
        jScrollPane1.setViewportView(areaTexto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtin, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                            .addComponent(txtpost)
                            .addComponent(txtres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtresActionPerformed

    private void txtinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinKeyPressed
    
    }//GEN-LAST:event_txtinKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(!txtin.getText().equals("")){
        evaluar();
        
        new Gui(txtin.getText()).setVisible(true);
    }
    else {
        showMessageDialog(null,"escribe una expresión de forma infija o natural");
        
    }      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
   
    
    /**
     * Metodo que evalua la exprecion posfija y devuelve el valor real de la evaluacion
     * @param posfijo es la exprecon en posfijo
     * @return  devuleve la evaluacion de la exprecion
     */
    public int evaluarPosfijo(String posfijo){              
        ArrayList<String> token = new ArrayList<String>();
        
        
        StringTokenizer st = new StringTokenizer(posfijo," ");
        while(st.hasMoreTokens()){
            token.add(st.nextToken());
        }
        
        if(token.size()==1){
            return Integer.parseInt(token.get(0));                
        }
        int c=0;
        areaTexto.append(token.toString()+"\n");
        while(token.size()!=1){
            
            String operador = token.get(c);
            if(operador.equals("+")||operador.equals("-")||operador.equals("*")||operador.equals("/")||operador.equals("^")){
                String operando1=token.get(c-1);
                String operando2 =token.get(c-2);
                
                token.remove(c);
                token.remove(c-1);
                token.remove(c-2);
                if(operador.equals("+")){
                    try {
                        String suma = (Integer.parseInt(operando2)+Integer.parseInt(operando1))+"";
                        token.add(c-2,suma);
                        c=0;
                    } catch (Exception e) {
                        areaTexto.setText("Error al comvertir un operando\n"+e);
                        return 0;
                    }                    
                }
                else if(operador.equals("-")){
                    try {
                        String resta = (Integer.parseInt(operando2)-Integer.parseInt(operando1))+"";
                        token.add(c-2,resta);
                        c=0;
                    } catch (Exception e) {
                        areaTexto.setText("Error al comvertir un operando\n"+e);
                        return 0;
                    }    
                }
                else if(operador.equals("*")){
                    try {
                        String multiplicacion = (Integer.parseInt(operando2)*Integer.parseInt(operando1))+"";
                        token.add(c-2,multiplicacion);
                        c=0;
                    } catch (Exception e) {
                        areaTexto.setText("Error al comvertir un operando\n"+e);
                        return 0;
                    }    
                }
                else if(operador.equals("/")){
                    try {
                        String divicion = (Integer.parseInt(operando2)/Integer.parseInt(operando1))+"";
                        token.add(c-2,divicion);
                        c=0;
                    } catch (Exception e) {
                        areaTexto.setText("Error al comvertir un operando\n"+e);
                        return 0;
                    }    
                }
                else{
                    try {
                        String potencia = (Integer.parseInt(operando2)^Integer.parseInt(operando1))+"";
                        token.add(c-2,potencia);
                        c=0;
                    } catch (Exception e) {
                        areaTexto.setText("Error al comvertir un operando\n"+e);
                        return 0;
                    }   
                }
                areaTexto.append(token.toString()+"\n");
            }
            else{
                c++;
            }
        }
        
        
        try {            
            return Integer.parseInt(token.get(0));            
        } catch (Exception e) {
            areaTexto.setText("Error al parsear el resultado\n"+e);
            return 0;
        }
        
    }
    
    
    /**
     * Metodo que se asegura que este correctamente planteda la exprecion
     * @param infijo es la exprecion matematica a evaluar
     * @return devuleve un true si la exprecion esta alternada correctamente, false de lo contrario
     */
    public boolean evaluarAlternaciones(String infijo){
        char[] cadena = infijo.toCharArray();
        char ultimoElemento=cadena[0];
        
        //Se da por entendido que todas las expreciones son correctas hasta que se encuentre un error
        boolean validacion=true;
    
        for(int c =1;c<cadena.length;c++){
            char caracter = cadena[c];
            if(Character.isDigit(caracter)){
                if(ultimoElemento==')'){
                    String salida = generarError(infijo,c);
                    salida+="No se puede poner un numero despues de un ')'\n";
                    areaTexto.append(salida);
                    validacion=false;                    
                }                                       
                else{
                    ultimoElemento=caracter;
                }
            }
            else if(caracter=='('){
                if(ultimoElemento=='('){
                    ultimoElemento=caracter;
                }
                else if(ultimoElemento=='+' || ultimoElemento=='-' || ultimoElemento=='*' || ultimoElemento=='/' || ultimoElemento=='^'){
                    ultimoElemento=caracter;
                }
                else{
                    String salida = generarError(infijo,c);
                    salida+="Despues de un '"+ultimoElemento+"' no puede venir un '('\n";
                    areaTexto.append(salida);
                    validacion=false;                         
                }                                    
            }
            else if(caracter==')'){
                if(ultimoElemento==')'){
                    ultimoElemento=caracter;
                }
                else if(Character.isDigit(ultimoElemento)){
                    ultimoElemento=caracter;
                }
                else{
                    String salida = generarError(infijo,c);
                    salida+="Despues de un '"+ultimoElemento+"' no puede venir un ')'\n";
                    areaTexto.append(salida);
                    validacion=false;                     
                }                                   
            }
            else if(caracter=='+' || caracter =='-' || caracter=='*' || caracter=='/' || caracter=='^'){
                if(ultimoElemento==')'){
                    ultimoElemento=caracter;
                }
                else if(Character.isDigit(ultimoElemento)){
                    ultimoElemento=caracter;
                }
                else{
                    String salida = generarError(infijo,c);
                    salida+="Despues de un '"+ultimoElemento+"' no puede venir un Operador '"+caracter+"'\n";
                    areaTexto.append(salida);
                    validacion=false;                   
                }                                       
            }            
        }       
        
        return validacion;
    }
    
    
    
    /**
     * Metodo que nos indica si la exprecion termina con un operador
     * @param infijo es la exprecion matematica
     * @return retorna true si empiesa con un operador, false de lo contrario
     */
    public boolean terminaConOperador(String infijo){
        char[] cadena = infijo.toCharArray();
        for(int c=cadena.length-1;c>0;c--){
            char caracter = cadena[c];
            if(Character.isDigit(caracter)){
                return false;
            }
            if(Character.isLetter(caracter)){
                return false;
            }
            else if(caracter=='+' || caracter =='-' || caracter=='*' || caracter=='/' || caracter=='^'){
                String salida = generarError(infijo,c);
                salida+="La expresion no puede terminar con operador";
                areaTexto.setText(salida);
                return true;
            }
        }
        return false;
    }   
    /**
     * Metodo que nos sirve para saber si la exprecion empiesa con un operador
     * @param infijo exprecion matematica
     * @return retorna true se empiesa con un Operador,false de lo contrario
     */
    public boolean empiesaConOperador(String infijo){
        char[] cadena = infijo.toCharArray();
        for(int c=0;c<cadena.length;c++){
            char caracter = cadena[c];
            if(Character.isDigit(caracter)){
                return false;
            }
            if(Character.isLetter(caracter)){
                return false;
            }
            else if(caracter=='+' || caracter =='-' || caracter=='*' || caracter=='/' || caracter=='^'){
                String salida = generarError(infijo,c);
                salida+="La exprecion no puede empezar con operador";
                areaTexto.setText(salida);
                return true;
            }
        }
        return false;
    }   
     /**
     * Metodo que transforma una exprecion de infijo a posfijo
     * @param operacion es la exprecion matematica
     * @return devuelve un String con la exprecion en postfijo o devuelve un null si la exprecion no se
     * evaluo correctamente
     */
    public String toPosfijo(String infijo){             
        String salida="";
        char[] cadena = infijo.toCharArray();
        
        for(int c=0;c<cadena.length;c++){
            char caracter = cadena[c];
            if(caracter=='('){
                pila.push(caracter);
            } 
            else if(caracter==')'){                
                while(true){
                    if(pila.empty()){
                        String retorno = generarError(infijo,c);
                        retorno+="Operacion invalida numero de parentesis impares";
                        areaTexto.setText(retorno);
                        return null;
                    }
                    char temp = pila.pop().charValue();
                    if(temp=='('){
                        break;
                    }
                    else{
                        salida+=" "+temp;
                    }
                }//fin del wile
            }
            else if(Character.isDigit(caracter)){
                salida+=" "+caracter;                
                c++;
     busqueda : for( ; c<cadena.length;c++){
                    if(Character.isDigit(cadena[c])){
                        salida+=cadena[c];
                    }
                    else{
                        c--;
                        break busqueda;
                    }                    
                }                
            }
            else if(caracter=='+'||caracter=='-'||caracter=='/'||caracter=='*'||caracter=='^'){   
                if(pila.empty()){
                    pila.push(caracter);
                }
                else{
                    while(true){                    
                        if(esDeMayorPresedencia(caracter)){                            
                            pila.push(caracter);
                            break;
                        }else{
                            salida+=" "+pila.pop();
                        }  
                    }                                 
                }               
            }
            else{
                String retorno = generarError(infijo,c);
                retorno+="caracter no valido para la exprecion : '"+caracter+"'";
                areaTexto.setText(retorno);
                return null;
            }
        }//fin del for
        if(!pila.empty()){
            do{
                char temp = pila.pop().charValue();
                salida+=" "+temp;                
            }while(!pila.empty());
        }
        
        return salida.trim();
    }
    
    
    /**
     * Metodo utilizado para validar que los parentesis estan correctamente
     * @param operacion esta variable deve de contener la operacion matematica a efectuar
     *  Ejemplo: a+((b-c)/d)
     * @return Devuelve true si la operacion tiene correctamente los parentesis
     */
    public boolean validarParentesis(String operacion){
        Stack<Character> pila = new Stack<Character>();
        
        char[] ecuacion = operacion.toCharArray();        
        for(int c=0;c<ecuacion.length;c++){
            char caracter = ecuacion[c];
            if(caracter=='('){
                pila.push(caracter);
            }
            else if(caracter==')'){
                if(pila.empty()){
                    String salida = generarError(operacion,c);
                    salida+="Numero de parentesis impares";
                    areaTexto.setText(salida);
                    return false;
                }
                else{
                    pila.pop();
                }
            }
        }
        if(!pila.empty()){    
            String salida = generarError(operacion,ecuacion.length-1);
            salida+="Numero de parentesis impares,Se esperaba que se cerrara los parentesis";
            areaTexto.setText(salida);
            return false;
            
        }        
        return true;
    }
    
    
    /**
     * Metodo que genera la posicion en la que se genero el error en tienpo de evaluar la expreción
     * @param infijo es la exprecion en infijo que se esta evaluando
     * @param indice es la pacion en que se encontro el error
     * @return devuelve el String indicando la posicion exacta del error
     */
    private String generarError(String infijo,int indice){
        String error[] = new String[infijo.length()];
        for(int c=0;c<error.length;c++){
            error[c]="  ";
        }
        error[indice]="^";
        
        String error2="";
        for(int c=0;c<error.length;c++){
            error2+=error[c];
        }
        return infijo+"\n"+error2+"\n";
    }
    private void limpiarCampos(){
        parentesis.setBackground(Color.gray);
        inicia.setBackground(Color.gray);
        fin.setBackground(Color.gray);
        exp.setBackground(Color.gray);
       
        txtpost.setText("");
        areaTexto.setText("");
        txtres.setText("");
    }
    
     private void evaluar(){
        limpiarCampos();
        String infija = txtin.getText();   
        
        //Validamos que la emprecion tecleada no este en blanco.
        if(infija.equals("")){
            return;
        }
        
       
        
        //Validamos que los parentesis esten correctamente anidados
        if(validarParentesis(infija)){
            parentesis.setBackground(Color.green);
            
        }
        else{
            parentesis.setBackground(Color.red);
            return;
        }
        
        
        //Validamos que la emprecion no inicie con un operador
        if(empiesaConOperador(infija)){inicia.setBackground(Color.red);
            return;
        }
        else{
            inicia.setBackground(Color.green);
            }
        
        
        //Validamos que la emprecion no termine con un operador
        if(terminaConOperador(infija)){
            fin.setBackground(Color.red);
            return;
        }
        else{
            fin.setBackground(Color.green);
            }
        
        
        
        //Evaluamos que la exprecion este correctamente alternada
        if(evaluarAlternaciones(infija)){
            exp.setBackground(Color.green);
              }
        else{
            exp.setBackground(Color.red);
            return;
        }
        
        
        //Evaluamos transformamos la exprecion a posfijo
        String posfija = toPosfijo(infija);
        if(posfija==null){
            return;
        }
        else{
            txtpost.setText(posfija);
        }
        
        int operacion = evaluarPosfijo(posfija);
        txtres.setText(operacion+"");
        
    }
    
    
    /**
     * Metodo utilizado para saber si el caracter es de mayor presedencia que el que se
     * encuentra en la ultma posicion de la pila
     * @param caracter caracter que se evaluara para meterse a la pila
     * @return devuelve true si el caracter es de mayor presedencia, false de lo contrario
     */
    private boolean esDeMayorPresedencia(char caracter){
        if(pila.empty()){
            return true;
        }
        if(caracter==pila.peek().charValue()){
            return false;
        }
        if(caracter=='^'){
            return true;
        }
        if( (caracter=='*'&&pila.peek().charValue()=='/')||(caracter=='/'&&pila.peek().charValue()=='*')){
            return false;
        }
        if( (caracter=='+'&&pila.peek().charValue()=='-')||(caracter=='-'&&pila.peek().charValue()=='+')){
            return false;
        }        
        else if(caracter=='-'||caracter=='+'){
            char temp = pila.peek().charValue();
            if(temp=='*'||temp=='/'){
                return false;
            }
        }
        return true;        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notacion_In_Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notacion_In_Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notacion_In_Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notacion_In_Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notacion_In_Post().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JPanel exp;
    private javax.swing.JPanel fin;
    private javax.swing.JPanel inicia;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel parentesis;
    private javax.swing.JTextField txtin;
    private javax.swing.JTextField txtpost;
    private javax.swing.JTextField txtres;
    // End of variables declaration//GEN-END:variables
}
