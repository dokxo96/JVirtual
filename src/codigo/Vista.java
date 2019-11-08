
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.security.Key;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    byte[] bytesImg;
    GestionArc gestion = new GestionArc();
    TextLineNumber lineas;
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
        lineas = new TextLineNumber(txtEntrada);
        lineas.setCurrentLineForeground(new Color(255,0,0));//current line
        lineas.setForeground(new Color(76, 175, 80));//color linea

        jScrollPane2.setRowHeaderView(lineas);
        jScrollPane2.setViewportView(txtEntrada); 
    }

     private void analizarLexico() throws IOException {
        
        int contLinea = 1;
        String expr = (String) txtEntrada.getText();

        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "Linea " + contLinea + "\t\t\tSimbolo\n";

        while (true) {
            Tokens token = lexer.yylex();

            if (token == null) {
                //txaResultado.setText(resultado + "\n Terminado");
                txaResultado.setText("");
                return;
            }
            DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
            String reservada = "Palabra reservada";
            String tipoDato = "Tipo de dato";
            String funcion = "Funcion";
            String operadorMat = "Operador matematico/relacional";
            String agrupacion = "Operador de agrupacion";
            
            //resultado+="Reservada Inicio_App\t\t"+lexer.lexeme+"\n"; --> Para que salga por un textArea
            switch (token) {                
                case Linea:
                    contLinea++;
                    break;
                    
                case Comillas:
                    Object filaComillas[] = {contLinea, lexer.lexeme, "Caracter"};
                    modelo.addRow(filaComillas);
                    break;
                
                case Igual:
                    Object filaIgual[] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaIgual);
                    break;
    
                case Suma:
                    Object filaSuma [] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaSuma);
                    break;
    
                case Resta:
                    Object filaResta [] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaResta);
                    break;
    
                case Multiplicacion:
                    Object filaMultiplicacion [] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaMultiplicacion);
                    break;
    
                case Division:
                    Object filaDivision [] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaDivision);
                    break;
                    
                case Parentesis_a:
                    Object filaParentesisA [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaParentesisA);
                    break;
    
                case Parentesis_c:
                    Object filaParentesisC [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaParentesisC);
                    break;
    
                case Llave_a:
                    Object filaLlaveA [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaLlaveA);
                    break;
                    
                case Llave_c:
                    Object filaLlaveC [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaLlaveC);
                    break;
                    
                case Corchete_a:
                    Object filaCorcheteA [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaCorcheteA);
                    break;
    
                case Corchete_c:
                    Object filaCorcheteC [] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaCorcheteC);
                    break;
                           
                case P_coma:
                    Object filaPuntoComa [] = {contLinea, lexer.lexeme, "Caracter"};
                    modelo.addRow(filaPuntoComa);
                    break;
    
                case Identificador:
                    Object filaIdentificador [] = {contLinea, lexer.lexeme, "Identificador"};
                    modelo.addRow(filaIdentificador);
                    break;
            
                case Numero:
                    Object filaNumero [] = {contLinea, lexer.lexeme, "Numero"};
                    modelo.addRow(filaNumero);
                    break;
            
                case ERROR:
                    Object filaError [] = {contLinea, lexer.lexeme, "Caracter no definido"};
                    modelo.addRow(filaError);
                    break;
    
                case Inicio_App:
                    Object filaInicioApp [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaInicioApp);
                    break;

                case Text:
                    Object filaText [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaText);
                    break;
    
                case Ent:
                    Object filaEnt [] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaEnt);
                    break;
                    
                case asignacion:
                    Object filaAsignacion [] = {contLinea, lexer.lexeme, "caracter reservado"};
                    modelo.addRow(filaAsignacion);
                    break;
    
                case punto:
                    Object filaPunto [] = {contLinea, lexer.lexeme, "caracter"};
                    modelo.addRow(filaPunto);
                    break;
    
                case Real:
                    Object filaReal [] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaReal);
                    break;
    
                case Bool:
                    Object filaBool [] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaBool);
                    break;
    
                case operadorBooleano:
                    Object filaOperadorBooleano [] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaOperadorBooleano); 
                    break;
                    
                case operadorIncrementoDecremento:
                    Object filaOperadorIncDec [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOperadorIncDec);
                    break;

                case operadorLogico:
                    Object filaOpLogico [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOpLogico);
                    break;
    
                case Car:
                    Object filaCar [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaCar);
                    break;
    
                case operadorRelacional:
                    Object filaOpRel [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOpRel);
                    break;
                    
                case Tarea:
                    Object filaTarea [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaTarea);
                    break;
    
                case Y_si:
                    Object filaYsi [] ={contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaYsi);
                    break;
    
                case Mientras:
                    Object filaMientras [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaMientras);
                    break;
                        
                case Imprime:
                    Object filaImprime [] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaImprime);
                    break;                

                    
                default:
                    resultado += "< " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaResultado = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnLexico = new javax.swing.JButton();
        btnSintactico = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSemantico = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaSimbolos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuItemAbrir = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 24, 94));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 750));

        txaResultado.setBackground(new java.awt.Color(0, 0, 0));
        txaResultado.setColumns(20);
        txaResultado.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txaResultado.setForeground(new java.awt.Color(255, 255, 255));
        txaResultado.setRows(5);
        txaResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));
        jScrollPane1.setViewportView(txaResultado);

        jPanel2.setBackground(new java.awt.Color(45, 61, 140));

        btnLexico.setBackground(new java.awt.Color(103, 70, 195));
        btnLexico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLexico.setForeground(new java.awt.Color(255, 255, 255));
        btnLexico.setText("Lexico");
        btnLexico.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLexico.setBorderPainted(false);
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });

        btnSintactico.setBackground(new java.awt.Color(103, 70, 195));
        btnSintactico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSintactico.setForeground(new java.awt.Color(255, 255, 255));
        btnSintactico.setText("Sintactico");
        btnSintactico.setBorderPainted(false);
        btnSintactico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSintactico.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        btnSintactico.setPreferredSize(new java.awt.Dimension(90, 40));
        btnSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSintacticoActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(103, 70, 195));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(103, 70, 195));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(90, 40));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSemantico.setBackground(new java.awt.Color(103, 70, 195));
        btnSemantico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSemantico.setForeground(new java.awt.Color(103, 70, 195));
        btnSemantico.setText("Semantico");
        btnSemantico.setBorderPainted(false);
        btnSemantico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSemantico.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        btnSemantico.setPreferredSize(new java.awt.Dimension(90, 40));
        btnSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemanticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLexico, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSintactico, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(256, 256, 256)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnSemantico, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLexico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSintactico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSemantico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(30, Short.MAX_VALUE)))
        );

        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Token", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaSimbolos);
        if (tablaSimbolos.getColumnModel().getColumnCount() > 0) {
            tablaSimbolos.getColumnModel().getColumn(0).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(1).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(2).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(3).setResizable(false);
        }

        txtEntrada.setBackground(new java.awt.Color(222, 227, 255));
        txtEntrada.setColumns(20);
        txtEntrada.setRows(5);
        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntradaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtEntrada);

        jScrollPane4.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuArchivo.setText("Archivo");

        jMenuItem4.setText("Nuevo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem4);

        menuItemAbrir.setText("Abrir");
        menuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemAbrir);

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem2);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem3);

        jMenuBar1.add(menuArchivo);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1211, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        //Limpiar tabla
        DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
        modelo.setRowCount(0);

        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLexicoActionPerformed

    private void btnSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSintacticoActionPerformed
        String tmp = txtEntrada.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(tmp)));

        try {
            s.parse();
            txaResultado.setText("Correcto");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txaResultado.setText("Error linea " + (sym.right + 1) + " " + sym.value);
        }
    }//GEN-LAST:event_btnSintacticoActionPerformed


    private void txtEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyPressed

    }//GEN-LAST:event_txtEntradaKeyPressed

    private void menuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAbrirActionPerformed
        if(seleccionado.showDialog(null, "ABRIR ARCHIVO") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("txt")){
                    String contenido = gestion.AbrirATexto(archivo);
                    txtEntrada.setText(contenido);
                }else{               
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de texto.");
                    }
            }
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed
    private String fileName="";
    private boolean g=false;
    private void guardar(){
        try {
            java.io.FileOutputStream fs = new java.io.FileOutputStream(fileName,true); //fs = Flujo de Salida
            byte b[]=txtEntrada.getText().getBytes();
            fs.write(b);
        } catch (FileNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
     if(!txtEntrada.getText().equals("")&&!g)
        if(javax.swing.JOptionPane.showConfirmDialog(this, "¿Desea Guardar los Cambios?")==0){
            
            /////////////////////
             if(seleccionado.showDialog(null, "GUARDAR TEXTO") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("txt")){
                String contenido = txtEntrada.getText();
                String respuesta = gestion.GuardarATexto(archivo, contenido);
                if(respuesta!=null){
                    JOptionPane.showMessageDialog(null, respuesta);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar texto.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El texto se debe guardar en un formato de texto.");
            }
        } 
            /////////////////////
            
        }//Guardar = si
        txtEntrada.setText(""); fileName=""; g=false;        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      if(seleccionado.showDialog(null, "GUARDAR TEXTO") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("txt")){
                String contenido = txtEntrada.getText();
                String respuesta = gestion.GuardarATexto(archivo, contenido);
                if(respuesta!=null){
                    JOptionPane.showMessageDialog(null, respuesta);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar texto.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El texto se debe guardar en un formato de texto.");
            }
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txaResultado.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
        modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemanticoActionPerformed
        String codigo = txtEntrada.getText();
    }//GEN-LAST:event_btnSemanticoActionPerformed

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLexico;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSemantico;
    private javax.swing.JButton btnSintactico;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JTable tablaSimbolos;
    private javax.swing.JTextArea txaResultado;
    private javax.swing.JTextArea txtEntrada;
    // End of variables declaration//GEN-END:variables
}

