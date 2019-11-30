
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

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
    private DefaultStyledDocument doc;
    static DefaultListModel listModel = new DefaultListModel();
    boolean f=true;

    
    public Vista() {
        //Asignar color a las palabras reservadas
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet red = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet Black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.blue);
        final AttributeSet gray = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.gray);
        final AttributeSet yellow = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.yellow);
        final AttributeSet orange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.orange);
        doc = new DefaultStyledDocument() {

            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = jTextPane1.getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        
                        //Si concide con la siguiente expresion se pinta de color
                        if (text.substring(wordL, wordR).matches("(\\W)*(true|false|start)")) {
                            setCharacterAttributes(wordL, wordR - wordL, orange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Inicio_App|Y_si|Tarea|Mientras|Gira_izq|Gira_der|Avanza|Retroceder|Alto|Advertencia|VerificarBateria|Aviso|Imprime|Durante|Repite|Ingresa|Text|Inc|Dec|Publica|Vibrar|Ir|Funcion|Repite|saltoLinea|Igual|Menor|Mayor|Mas|Resta|Multiplicacion|Division|Potencia|PuntoComa|llaveApertura|llaveCierre|ParentesisApertura|ParentesisCierre|Identificador|Numero|ERROR)")) {
                            setCharacterAttributes(wordL, wordR - wordL, blue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(->|^[a-zA-Z0-9_.-]*$)")) {
                            setCharacterAttributes(wordL, wordR - wordL, gray, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Ent|Real|RealExt|Bool|Car|Text)")) {
                            setCharacterAttributes(wordL, wordR - wordL, red, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(\\Q) (\\W)* (\\E)")) {
                            setCharacterAttributes(wordL, wordR - wordL, yellow, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, Black, false);
                        }

                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                String text = jTextPane1.getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);
                //Si no coincide se elimina el color
                if (text.substring(before, after).matches("(\\W)*(true|false|start)")) {
                    setCharacterAttributes(before, after - before, orange, false);
                } else if (text.substring(before, after).matches("(\\W)*(Inicio_App|Y_si|Tarea|Mientras|Gira_izq|Gira_der|Avanza|Retroceder|Alto|Advertencia|VerificarBateria|Aviso|Imprime|Durante|Repite|Ingresa|Text|Inc|Dec|Publica|Vibrar|Ir|Funcion|Repite|saltoLinea|Igual|Menor|Mayor|Mas|Resta|Multiplicacion|Division|Potencia|PuntoComa|llaveApertura|llaveCierre|ParentesisApertura|ParentesisCierre|Identificador|Numero|ERROR)")) {
                    setCharacterAttributes(before, after - before, blue, false);
                } else if (text.substring(before, after).matches("(\\W)*(->|^[a-zA-Z0-9_.-]*$)")) {
                    setCharacterAttributes(before, after - before, gray, false);
                } else if (text.substring(before, after).matches("(\\W)*(Ent|Real|RealExt|Bool|Car|Text)")) {
                    setCharacterAttributes(before, after - before, red, false);
                } else {
                    setCharacterAttributes(before, after - before, Black, false);
                }
            }
        };
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        lineas = new TextLineNumber(jTextPane1);
        lineas.setCurrentLineForeground(new Color(255, 0, 0));//current line
        lineas.setForeground(new Color(76, 175, 80));//color linea
        lineas.setBackground(new Color(55, 71, 79));
        jTextPane1.setBackground(Color.darkGray);
        jScrollPane4.setRowHeaderView(lineas);
        jScrollPane4.setViewportView(jTextPane1);

        jTextPane1.requestFocus();

        //tp.setBackground(Color.DARK_GRAY);
        txaResultado.setForeground(Color.red);
    }

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

   

    private void analizarLexico() throws IOException {

        int contLinea = 1;
        String expr = (String) jTextPane1.getText();

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
            String operadorMat = "Operador matematico/relacional";
            String agrupacion = "Operador de agrupacion";
                       
            
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
                    Object filaSuma[] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaSuma);
                    break;

                case Resta:
                    Object filaResta[] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaResta);
                    break;

                case Multiplicacion:
                    Object filaMultiplicacion[] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaMultiplicacion);
                    break;

                case Division:
                    Object filaDivision[] = {contLinea, lexer.lexeme, operadorMat};
                    modelo.addRow(filaDivision);
                    break;

                case Parentesis_a:
                    Object filaParentesisA[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaParentesisA);
                    break;

                case Parentesis_c:
                    Object filaParentesisC[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaParentesisC);
                    break;

                case Llave_a:
                    Object filaLlaveA[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaLlaveA);
                    break;

                case Llave_c:
                    Object filaLlaveC[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaLlaveC);
                    break;

                case Corchete_a:
                    Object filaCorcheteA[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaCorcheteA);
                    break;

                case Corchete_c:
                    Object filaCorcheteC[] = {contLinea, lexer.lexeme, agrupacion};
                    modelo.addRow(filaCorcheteC);
                    break;

                case P_coma:
                    Object filaPuntoComa[] = {contLinea, lexer.lexeme, "Caracter"};
                    modelo.addRow(filaPuntoComa);
                    break;

                case Identificador:
                    Object filaIdentificador[] = {contLinea, lexer.lexeme, "Identificador"};
                    modelo.addRow(filaIdentificador);
                    break;

                case Numero:
                    Object filaNumero[] = {contLinea, lexer.lexeme, "Numero"};
                    modelo.addRow(filaNumero);
                    break;               

                case Inicio_App:
                    Object filaInicioApp[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaInicioApp);
                    break;

                case Text:
                    Object filaText[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaText);
                    break;

                case Ent:
                    Object filaEnt[] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaEnt);
                    break;

                case asignacion:
                    Object filaAsignacion[] = {contLinea, lexer.lexeme, "caracter reservado"};
                    modelo.addRow(filaAsignacion);
                    break;

                case punto:
                    Object filaPunto[] = {contLinea, lexer.lexeme, "caracter"};
                    modelo.addRow(filaPunto);
                    break;

                case Real:
                    Object filaReal[] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaReal);
                    break;

                case Bool:
                    Object filaBool[] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaBool);
                    break;

                case operadorBooleano:
                    Object filaOperadorBooleano[] = {contLinea, lexer.lexeme, tipoDato};
                    modelo.addRow(filaOperadorBooleano);
                    break;

                case operadorIncrementoDecremento:
                    Object filaOperadorIncDec[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOperadorIncDec);
                    break;

                case operadorLogico:
                    Object filaOpLogico[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOpLogico);
                    break;

                case Car:
                    Object filaCar[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaCar);
                    break;

                case operadorRelacional:
                    Object filaOpRel[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaOpRel);
                    break;

                case Tarea:
                    Object filaTarea[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaTarea);
                    break;

                case Y_si:
                    Object filaYsi[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaYsi);
                    break;

                case Mientras:
                    Object filaMientras[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaMientras);
                    break;

                case Imprime:
                    Object filaImprime[] = {contLinea, lexer.lexeme, reservada};
                    modelo.addRow(filaImprime);
                    break;
                                                        
                                       
                /*------------------------------------------------------------*/
              case ERROR:
                   Object filaError [] = {contLinea, lexer.lexeme, "Caracter no definido"};
                    modelo.addRow(filaError);
                    console.setForeground(Color.red);
                    listModel.addElement("Error_Type::Lexico:: no se reconoce el caracter  '"+lexer.lexeme+ "'  en la linea -> "+contLinea);
                    console.setModel(listModel);
                    f=false;
                    break;

                default:
                    resultado += "< " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }

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
        btnSemantico = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        btnTmp = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaSimbolos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane(doc);
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuArchivo = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuItemAbrir = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setTitle("Analizador ");
        setName("ANALIZADOR"); // NOI18N
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 10, 18));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 750));

        txaResultado.setBackground(new java.awt.Color(0, 0, 0));
        txaResultado.setColumns(20);
        txaResultado.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txaResultado.setForeground(new java.awt.Color(255, 255, 255));
        txaResultado.setRows(5);
        txaResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));
        jScrollPane1.setViewportView(txaResultado);

        jPanel2.setBackground(new java.awt.Color(69, 90, 100));

        btnLexico.setBackground(new java.awt.Color(153, 153, 153));
        btnLexico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLexico.setText("Lexico");
        btnLexico.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLexico.setBorderPainted(false);
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });

        btnSintactico.setBackground(new java.awt.Color(153, 153, 153));
        btnSintactico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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

        btnSemantico.setBackground(new java.awt.Color(153, 153, 153));
        btnSemantico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnLimpiar.setBackground(new java.awt.Color(38, 50, 56));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-broom-32Black.png"))); // NOI18N
        btnLimpiar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-broom-32 Green.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnCompilar.setBackground(new java.awt.Color(38, 50, 56));
        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-play-32Blacn.png"))); // NOI18N
        btnCompilar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-play-32Green.png"))); // NOI18N
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnTmp.setText("Tmp");
        btnTmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTmpActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSemantico, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTmp)
                .addGap(17, 17, 17)
                .addComponent(btnCompilar)
                .addGap(12, 12, 12)
                .addComponent(btnLimpiar)
                .addGap(113, 113, 113)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnLexico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSintactico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSemantico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnTmp))
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCompilar))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        tablaSimbolos.setBackground(new java.awt.Color(55, 71, 79));
        tablaSimbolos.setForeground(new java.awt.Color(255, 255, 255));
        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Token", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSimbolos.setOpaque(false);
        jScrollPane3.setViewportView(tablaSimbolos);
        if (tablaSimbolos.getColumnModel().getColumnCount() > 0) {
            tablaSimbolos.getColumnModel().getColumn(0).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaSimbolos.getColumnModel().getColumn(1).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(1).setPreferredWidth(30);
            tablaSimbolos.getColumnModel().getColumn(2).setResizable(false);
            tablaSimbolos.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jTextPane1.setOpaque(false);
        jScrollPane4.setViewportView(jTextPane1);
        jTextPane1.setBackground(new java.awt.Color(55, 71, 79));

        jTextPane1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jTextPane1.setForeground(new java.awt.Color(255, 255, 255));

        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));
        console.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(console);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(69, 90, 100));
        jMenuBar1.setForeground(new java.awt.Color(69, 90, 100));
        jMenuBar1.setBorderPainted(false);

        jMenu2.setBackground(new java.awt.Color(69, 90, 100));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-sun-glasses-32.png"))); // NOI18N
        jMenu2.setEnabled(false);
        jMenu2.setOpaque(true);
        jMenu2.setRequestFocusEnabled(false);
        jMenu2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-sun-glasses-32.png"))); // NOI18N
        jMenuBar1.add(jMenu2);

        menuArchivo.setBackground(new java.awt.Color(69, 90, 100));
        menuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-folder-32.png"))); // NOI18N
        menuArchivo.setToolTipText("ARCHIVO");
        menuArchivo.setOpaque(true);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-file-32.png"))); // NOI18N
        jMenuItem4.setToolTipText("NUEVO");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem4);

        menuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-open-door-32.png"))); // NOI18N
        menuItemAbrir.setToolTipText("ABRIR");
        menuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemAbrir);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-32.png"))); // NOI18N
        jMenuItem2.setToolTipText("GUARDAR");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem2);

        jMenuBar1.add(menuArchivo);

        jMenu3.setBackground(new java.awt.Color(69, 90, 100));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-conference-32.png"))); // NOI18N
        jMenu3.setToolTipText("EQUIPO");
        jMenu3.setOpaque(true);
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenu1.setBackground(new java.awt.Color(69, 90, 100));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-web-help-32.png"))); // NOI18N
        jMenu1.setToolTipText("AYUDA");
        jMenu1.setOpaque(true);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-abc-32.png"))); // NOI18N
        jMenuItem3.setToolTipText("PALABRAS RESERVADAS");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-logic-data-types-32.png"))); // NOI18N
        jMenuItem5.setToolTipText("TIPOS DE VARIABLES");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-kackstiftaufpapier-32.png"))); // NOI18N
        jMenuItem6.setToolTipText("DECLARACIONES");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-hierarchy-32.png"))); // NOI18N
        jMenuItem7.setToolTipText("ESTRUCTURA GENERAL");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenu3.add(jMenu1);

        jMenuBar1.add(jMenu3);

        jMenu5.setBackground(new java.awt.Color(69, 90, 100));
        jMenu5.setText("                                                                                                                                                                                                                                                                                                                                          ");
        jMenu5.setEnabled(false);
        jMenu5.setOpaque(true);
        jMenuBar1.add(jMenu5);

        jMenu4.setBackground(new java.awt.Color(69, 90, 100));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-popup-window-32.png"))); // NOI18N
        jMenu4.setOpaque(true);
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setBackground(new java.awt.Color(69, 90, 100));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-close-window-32.png"))); // NOI18N
        jMenu6.setOpaque(true);
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1257, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
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
        String tmp = jTextPane1.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(tmp)));

        try {
            s.parse();
            //txaResultado.setText("Correcto");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            //txaResultado.setText("Error linea" + (sym.right + 1) + " " + sym.value);
        }
    }//GEN-LAST:event_btnSintacticoActionPerformed


    private void menuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAbrirActionPerformed
        if (seleccionado.showDialog(null, "ABRIR ARCHIVO") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String contenido = gestion.AbrirATexto(archivo);
                    jTextPane1.setText(contenido);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de texto.");
                }
            }
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed
    private String fileName = "";
    private boolean g = false;

    private void guardar() {
        try {
            java.io.FileOutputStream fs = new java.io.FileOutputStream(fileName, true); //fs = Flujo de Salida
            byte b[] = jTextPane1.getText().getBytes();
            fs.write(b);
        } catch (FileNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (!jTextPane1.getText().equals("") && !g) {
            if (javax.swing.JOptionPane.showConfirmDialog(this, "¿Desea Guardar los Cambios?") == 0) {

                /////////////////////
                if (seleccionado.showDialog(null, "GUARDAR TEXTO") == JFileChooser.APPROVE_OPTION) {
                    archivo = seleccionado.getSelectedFile();
                    if (archivo.getName().endsWith("txt")) {
                        String contenido = jTextPane1.getText();
                        String respuesta = gestion.GuardarATexto(archivo, contenido);
                        if (respuesta != null) {
                            JOptionPane.showMessageDialog(null, respuesta);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar texto.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El texto se debe guardar en un formato de texto.");
                    }
                }
                /////////////////////

            }//Guardar = si
        }
        jTextPane1.setText("");
        fileName = "";
        g = false;        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (seleccionado.showDialog(null, "GUARDAR TEXTO") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String contenido = jTextPane1.getText();
                String respuesta = gestion.GuardarATexto(archivo, contenido);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(null, respuesta);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar texto.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El texto se debe guardar en un formato de texto.");
            }
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemanticoActionPerformed
        String codigo = jTextPane1.getText();
    }//GEN-LAST:event_btnSemanticoActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
          //Limpiar tabla
        txaResultado.setText("");
        console.setModel(new DefaultListModel());
        listModel.clear();
        
        DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
        modelo.setRowCount(0);

       String tmp = jTextPane1.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(tmp)));

        try {
            analizarLexico();
            s.parse();
            if(f){showMessageDialog(null,f);
            console.setForeground(Color.white);
            listModel.addElement("Analisis léxico y Sintactico Correcto!");
            console.setModel(listModel);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {   
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            Symbol sym = s.getS();
            console.setForeground(Color.red);
            listModel.addElement("Error_Type::Sintaxis::  Linea->" + (sym.right+1  ) + ", Columna-> " + (sym.left+1 ) + ", Error antes de::  \"" + sym.value + "\"");
            console.setModel(listModel);
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txaResultado.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
        modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        System.exit(0);  // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JTextArea msg = new JTextArea(
                "PALABRA              |     DESCRIPCIÓN"
                + "\n"
                + "Inicio_App             --INICIA CODIGO        "
                + "\n"
                + "Y_si                        --CONDICIONAL          "
                + "\n"
                + "Tarea                     --DECLARA MÉTODO       "
                + "\n"
                + "Mientras                --CICLO REPETITIVO"
                + "\n"
                + "Gira_izq"
                + "\n"
                + "Gira_der"
                + "\n"
                + "Avanza"
                + "\n"
                + "Retroceder"
                + "\n"
                + "Alto"
                + "\n"
                + "Advertencia"
                + "\n"
                + "VerificarBateria"
                + "\n"
                + "Aviso"
                + "\n"
                + "Imprime"
                + "\n"
                + "Durante"
                + "\n"
                + "Repite"
                + "\n"
                + "Ingresa"
                + "\n"
                + "Text"
                + "\n"
                + "Inc"
                + "\n"
                + "Dec"
                + "\n"
                + "Publica"
                + "\n"
                + "Vibrar"
                + "\n"
                + "Ir"
                + "\n"
                + "Funcion"
                + "\n"
                + "Repite"
                + "\n"
                + "saltoLinea"
                + "\n"
                + "Igual"
                + "\n"
                + "Menor"
                + "\n"
                + "Mayor"
                + "\n"
                + "Mas"
                + "\n"
                + "Resta"
                + "\n"
                + "Multiplicacion"
                + "\n"
                + "Division"
                + "\n"
                + "Potencia"
                + "\n"
                + "PuntoComa"
                + "\n"
                + "llaveApertura"
                + "\n"
                + "llaveCierre"
                + "\n"
                + "ParentesisApertura"
                + "\n"
                + "ParentesisCierre"
                + "\n"
                + "Identificador"
                + "\n"
                + "Numero"
                + "\n"
                + "ERROR");
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setSize(450, 550);
        msg.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msg);
        scrollPane.setSize(455, 555);

        JOptionPane.showMessageDialog(null, scrollPane);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JOptionPane.showMessageDialog(null,
                "PALABRA              |     DESCRIPCIÓN"
                + "\n"
                + "Ent                          |   --DECLARA UNA VARIABLE DE TIPO ENTERO"
                + "\n"
                + "Real                       |   --DECLARA UNA VARIABLE DE TIPO REAL"
                + "\n"
                + "RealExt                   |  --DECLARA UNA VARIABLE DE TIPO REAL EXTENDIDA"
                + "\n"
                + "Bool                          |--DECLARA UNA VARIABLE DE TIPO BOOLEANA"
                + "\n"
                + "Car                           |--DECLARA UNA VARIABLE DE TIPO CARACTER"
                + "\n"
                + "Text                          |--DECLARA UNA VARIABLE DE TIPO CADENA"
                + "\n"
        );        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JTextArea msg = new JTextArea(
                "Tipo Identificador <- valor ;"
                + "\n"
                + "\n"
                + "ENTEROS" + "\n"
                + "Ent x;" + "\n"
                + "Ent x <- 9;"
                + "\n"
                + "\n"
                + "_________________________________"
                + "REALES" + "\n"
                + "Real x;" + "\n"
                + "Real x <- 9.5;" + "\n"
                + "RealExt x;" + "\n"
                + "RealExt x <- 9.5;"
                + "\n"
                + "\n"
                + "_________________________________"
                + "BOOLEANOS" + "\n"
                + "Bool x;" + "\n"
                + "Bool x <- true|false;"
                + "\n"
                + "\n"
                + "_________________________________"
                + "CADENAS" + "\n"
                + "Text x;" + "\n"
                + "Text x <- 'Cadena de texto';"
                + "\n"
                + "\n"
                + "_________________________________"
                + "CARACTERES" + "\n"
                + "Car x;" + "\n"
                + "Car x <- 'X';"
        );
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setSize(250, 350);
        msg.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msg);

        JOptionPane.showMessageDialog(null, scrollPane);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JTextArea msg = new JTextArea(
                "Inicio_App ejemplo{\n                                      "
                + "Tarea(ent a=0,ent b=2, ent c=3){\n                         "
                + "Imprime('Las Variables son '+a+b+c);\n                                                           "
                + "}//Tarea\n                              "
                + "}//Inicip_App\n                                                                                  "
                + "\n"
                + "________________________________________________"
                + "Inicio_App ejemplo2{\n"
                + " Repite(i=0;i<10;i++;){\n"
                + "Y_si(i==6){\n"
                + "Alto"
                + "}\n"
                + "}\n"
                + "\n"
                + "Inicio_App ejemplo3{\n"
                + "Ent a <-5;"
                + "Durante(a<10){\n"
                + "Regresa(a)"
                + "}\n"
                + "Y_si(a=9){\n"
                + "Label1;\n"
                + "}\n"
                + "}"
                + "\n"
        );
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setSize(250, 350);
        msg.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(msg);

        JOptionPane.showMessageDialog(null, scrollPane);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked

    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        super.setExtendedState(ICONIFIED);      
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void btnTmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTmpActionPerformed

    }//GEN-LAST:event_btnTmpActionPerformed

   public static void setError(String error) {
        listModel.addElement(error);
    }
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
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnLexico;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSemantico;
    private javax.swing.JButton btnSintactico;
    private javax.swing.JButton btnTmp;
    private javax.swing.JList<String> console;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JTable tablaSimbolos;
    public static javax.swing.JTextArea txaResultado;
    // End of variables declaration//GEN-END:variables
}
