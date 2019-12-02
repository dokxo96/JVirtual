
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
import java.util.LinkedList;
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
    static ArrayList<ArryL> Arr = new ArrayList<ArryL>();
    LinkedList<Object[]> tablaS;
    static DefaultListModel palabrasRes = new DefaultListModel();
    
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
                        if (text.substring(wordL, wordR).matches("(\\W)*(Verdad|Falso|start)")) {
                            setCharacterAttributes(wordL, wordR - wordL, orange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Inicio_App|Y_si|Tarea|Mientras|Gira_izq|Gira_der|Avanza|Retroceder|Alto|Advertencia|VerificarBateria|Aviso|Imprime|Durante|Repite|Ingresa|Text|Inc|Dec|Publica|Vibrar|Ir|Funcion|Repite|saltoLinea|Igual|Menor|Mayor|Mas|Resta|Multiplicacion|Division|Potencia|PuntoComa|Identificador|Numero|ERROR)")) {
                            setCharacterAttributes(wordL, wordR - wordL, blue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(->|^[a-zA-Z0-9_.-]*$)")) {
                            setCharacterAttributes(wordL, wordR - wordL, gray, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(|llaveApertura|llaveCierre|ParentesisApertura|ParentesisCierre|Ent|Real|RealExt|Bool|Car|Text)")) {
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
        tablaS = new LinkedList<>();
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
        this.tablaS.clear();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "Linea " + contLinea + "\t\t\tSimbolo\n";

        while (true) {
            Tokens token = lexer.yylex();
            String dato = lexer.lexeme.toString();
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
            
            System.out.print("\n Aqui anda valiendo \n\n\n\n"+"  lexer:: "+lexer.lexeme.toString()+"  token:"+token.toString());
            this.tablaS.add(new Object[]{lexer.lexeme.toString(),token.toString()});          
            
            switch (token) {
                case Linea:
                    contLinea++;
                    break;

                case Comillas:
                    Object filaComillas[] = {contLinea, lexer.lexeme, "Comillas"};
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
                    Object filaPuntoComa[] = {contLinea, lexer.lexeme, "P_coma"};
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
                    Object filaPunto[] = {contLinea, lexer.lexeme, "punto"};
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
                    Object filaCar[] = {contLinea, lexer.lexeme, "cadenas"};
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
        jButton1 = new javax.swing.JButton();
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
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
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

        jButton1.setBackground(new java.awt.Color(38, 50, 56));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-estimate-32.png"))); // NOI18N
        jButton1.setMnemonic('E');
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-estimate-32 (1).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(52, 52, 52)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(491, Short.MAX_VALUE))
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
                    .addComponent(btnCompilar)
                    .addComponent(jButton1))
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        jMenuBar1.setBackground(new java.awt.Color(69, 90, 100));
        jMenuBar1.setForeground(new java.awt.Color(69, 90, 100));
        jMenuBar1.setBorderPainted(false);

        jMenu2.setBackground(new java.awt.Color(69, 90, 100));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-sun-glasses-32.png"))); // NOI18N
        jMenu2.setEnabled(false);
        jMenu2.setRequestFocusEnabled(false);
        jMenu2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-sun-glasses-32.png"))); // NOI18N
        jMenuBar1.add(jMenu2);

        menuArchivo.setBackground(new java.awt.Color(69, 90, 100));
        menuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-folder-32.png"))); // NOI18N
        menuArchivo.setToolTipText("ARCHIVO");

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

        jMenu1.setBackground(new java.awt.Color(69, 90, 100));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-web-help-32.png"))); // NOI18N
        jMenu1.setToolTipText("AYUDA");

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

        jMenuBar1.add(jMenu1);

        jMenu3.setBackground(new java.awt.Color(69, 90, 100));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-conference-32.png"))); // NOI18N
        jMenu3.setToolTipText("EQUIPO");
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
        jMenuBar1.add(jMenu3);

        jMenu5.setBackground(new java.awt.Color(69, 90, 100));
        jMenu5.setText("                                                                                                                                                                                                                                                                                                                                          ");
        jMenu5.setEnabled(false);
        jMenuBar1.add(jMenu5);

        jMenu4.setBackground(new java.awt.Color(69, 90, 100));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-popup-window-32.png"))); // NOI18N
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setBackground(new java.awt.Color(69, 90, 100));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-close-window-32.png"))); // NOI18N
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
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
            analizadorSemantico();
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
        console.setModel(new DefaultListModel());
        modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        System.exit(0);  // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(null,
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
                + "Gira_izq                  Gira_der"
                + "\n"
                + "Avanza                     Retroceder"
                + "\n"
                + "Alto                           Advertencia"
                + "\n"
                + "VerificarBateria      Aviso"
                + "\n"
                + "Imprime                  Durante"
                + "\n"
                + "Repite                      Ingresa"
                + "\n"
                + "Text                           Inc"
                + "\n"
                + "Dec                           Publica"
                + "\n"
                + "Vibrar                        Ir"
                + "\n"
                + "Funcion                    Repite"
                + "\n"
                + "saltoLinea                Igual"
                + "\n"
                + "Menor                        Mayor"
                + "\n"
                + "Mas                            Resta"
                + "\n"
                + "Multiplicacion           Division"
                + "\n"
                + "Potencia                    PuntoComa"
                + "\n"
                + "llaveApertura            llaveCierre"
                + "\n"
                + "ParentesisApertura  ParentesisCierre"
                + "\n"
                + "Identificador              Numero"
                + "\n"
                + "ERROR"
        ); 
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
        );  
      
          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
         JOptionPane.showMessageDialog(null,
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
        );         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
         JOptionPane.showMessageDialog(null,
                "Inicio_App ejemplo{\n                                      "
                + "Tarea(ent a=0,ent b=2, ent c=3){\n                         "
                + "Imprime('Las Variables son '+a+b+c);\n                                                           "
                + "}//Tarea\n                              "
                + "}//Inicip_App\n                                                                                  "
                + "\n"
                + "________________________________________________"
                + "\n"
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
       );        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
      Info in = new Info();
        in.setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        super.setExtendedState(ICONIFIED);      
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void btnTmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTmpActionPerformed

    }//GEN-LAST:event_btnTmpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Notacion_In_Post n = new Notacion_In_Post();
    n.setVisible(true);
    // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
   private String fileName = "";
    private boolean g = false;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////Analisis Semantico///////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
   private void analizadorSemantico() throws IOException{
        LinkedList<String> variables = new LinkedList<>();
        LinkedList<Object[]> variablesTabla = new LinkedList<>();
        LinkedList<String> funciones = new LinkedList<>();
        LinkedList<String> parametros = new LinkedList<>();
        LinkedList<Object[]> expresion = new LinkedList<>();
        LinkedList<String> expresiones = new LinkedList<>();
        LinkedList<Object[]> funcionesTabla = new LinkedList<>();
        
        //Variables de banderas
        Boolean evalInit = false;
        Boolean evalFun = false;
        Boolean evalDes = false;
        Boolean evalCic = false;
        Boolean asignacion = false;
        Boolean evalPRMOV = false;
        Boolean cuerpo = false;
        Boolean param = false;
        
        //Objeto principal
        String token = "";
        String obj = "";
        
        //Variables de uso temporales
        String tipo = "";
        String var = "";
        String temp = "";
        String aux = "";
        
        //Variables de uso de comparacion entre elementos
        String vartype1 = "";
        String vartype2 = "";
        
        //Contador de linea
        int counter = 1;
        
        LinkedList<Object[]> prcomTabla = new LinkedList<>();
        prcomTabla.add(new Object[]{"color","detectarColor"});
        prcomTabla.add(new Object[]{"color","detectarParada"});
        prcomTabla.add(new Object[]{"verdad","detectarAnomalia"});
        prcomTabla.add(new Object[]{"verdadero","estadoCamara"});
        prcomTabla.add(new Object[]{"tiempo","duracionRecorrido"});
        prcomTabla.add(new Object[]{"color","detectarLinea"});
        prcomTabla.add(new Object[]{"verdad","obstaculo"});
        
        //Inicio del analisis semantico
        System.out.println("Inicio del programa");
        for(Object[] elem: this.tablaS){
            obj = elem[0].toString();
            token = elem[1].toString();
            
            //Detectar que estamos en la parte de inicializacion de variables
            if(token.equals("Linea")){
                counter++;
                System.out.println("se detecto un salto de linea");
                
                continue;
                
            }
            
            if(token.equals("Inicio_App")){
                evalInit = true; //Cambiamos la bandera para que pueda leer las variables declaradas en el programa
                System.out.println("token inicio_App");
            } else
            if(token.equals("Tarea")){
                evalFun = true; //Cambiamos la bandera para que pueda leer el cuerpo de una funcion
                System.out.println("token Tarea");
            } else
            if(token.equals("Y_si")){
                temp = "";
                var = "";
                tipo = "";
                vartype1 = "";
                vartype2 = "";
                System.out.println("token Y_Si");
                evalDes = true; //Cambiamos la bandera para que pueda leer la estrcutra de decision
            } else
            if(token.equals("Mientras")){
                temp = "";
                var = "";
                tipo = "";
                vartype1 = "";
                vartype2 = "";
                System.out.println("token mientras  ");
                evalCic = true; //Cambiamos la bandera para que pueda leer la estructura de ciclo de mientras
            } 
            
            
            //Analisis de variables bandera evalInit para inicializacion
            if(evalInit){
                System.out.println("Declaracion de inicializacion \n");
                System.out.println(token);
                switch (token) {
                    case "Llave_a":
                        break;
                    case "Bool":
                        tipo = token;
                        break;
                    case "Ent":
                        tipo = token;
                        break;
                    case "Text":
                        tipo = token;
                        break;
                    case "Identificador": //Evaluamos que la variable no haya sido ya declarada, de ser asi, colocara la variable que lo guarda en vacio para que no lo guarde una vez exista una comparacion con lo que se esta declarando.
                        var = obj;
                        if(variables.contains(var)){
                            listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". La variable \"" + var + "\" ya fue declarada.");
                            var = "";
                           
                        }else{
                        if(!var.isEmpty()){
                                variables.add(var);
                                variablesTabla.add(new Object[]{tipo, var});
                                 var = "";
                            }
                        }
                        break;
                    case "Verdad": 
                        if(!"Bool".equals(tipo))
                            listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". Declaracion incorrecta de tipos " + tipo + " no corresponde a Bool.");
                        else{
                            if(!var.isEmpty()){
                                variables.add(var);
                                variablesTabla.add(new Object[]{tipo, var});
                            }
                        }
                        break;
                    case "Falso": 
                        if(!"Bool".equals(tipo))
                           listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". Declaracion incorrecta de tipos " + tipo + " se esperaba del tipo  Bool.");
                        else{
                            if(!var.isEmpty()){
                                variables.add(var);
                                variablesTabla.add(new Object[]{tipo, var});
                            }
                        }
                        break;
                    case "Car": 
                        if(!"Text".equals(tipo))
                            listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". Declaracion incorrecta de tipos " + tipo + " se esperaba del tipo Text.");
                        else{
                            if(!var.isEmpty()){
                                variables.add(var);
                                variablesTabla.add(new Object[]{tipo, var});
                            }
                        }
                        break;
                    
                
                }
                if(token.equals("Llave_c")){
                    evalInit = false;
                    System.out.println("Fin de prog");
                }
            }
            else 
            //analisis de palabras de movimiento que requieren de parametros
            if(evalPRMOV) {
                System.out.println("Entrando a evaluacion de PARAMETROS DE MOVIMIENTO");
                switch(token){
                    case "Tarea":
                        aux = token;
                        break;
                    case "esperar":
                        aux = token;
                        break;
                    case "identificador":
                        temp = obtenerTipo(variablesTabla, obj);
                        System.out.println(temp);
                        if(temp.isEmpty())
                            temp = obtenerTipoFuncion(funcionesTabla,obj);
                        if(!variables.contains(obj) && !funciones.contains(obj) && !parametros.contains(obj)){
                            listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". La variable o funcion \"" + obj + "\" no ha sido declarada.");
                        } else if(tipo.equals("avanzar") && !temp.equals("velocidad") )
                            listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". La variable o funcion \"" + obj + "\" no corresponde al tipo velocidad.");
                        else if(tipo.equals("esperar") && !temp.equals("tiempo")) 
                               listModel.addElement("Error_Type::Semantico::\nLinea: " + counter + ". La variable \"" + obj + "\" no corresponde al tipo tiempo.");
                        break;
                    case "veloc":
                        if(!aux.equals("avanzar"))
                            listModel.addElement("Error semantico de parametros en metodo de movimiento. Linea: " + counter + ". Parametro velocidad es solo aplicable al metodo avanzar");
                        break;
                    case "time": 
                        if(!aux.equals("esperar"))
                            listModel.addElement("Error semantico de parametros en metodo de movimiento. Linea: " + counter + ". Parametro tiempo es solo aplicable al metodo esperar");
                        break;
                    case "parentesis_a":
                        tipo = token;
                    case "parentesis_c":
                        if(temp.isEmpty() && !tipo.equals("parentesis_a"))
                            listModel.addElement("Error semantico de parametros en metodo de movimiento. Linea: " + counter + ". El metodo necesita de un parametro");
                        tipo = token;
                        break;
                }
                if(token.equals("parentesis_c")&& !tipo.equals("parentesis_a")){
                    evalPRMOV = false;
                    System.out.println("Saliendo de evaluación de PARAMETROS DE MOVIMIENTO");
                }
            } 
            else
            //Analizar una asignación cuando esta entra en parte del cuerpo del ciclo
            if(asignacion){
                System.out.println("Entrando a evaluacion para expresion de ASIGNACION");
                switch (token) {  
                    case "Igual":
                        expresion.add(elem);
                        break;
                    case "Identificador":
                        expresion.add(elem);
                        break;
                    case "Verdad":
                        expresion.add(elem);
                        break;
                    case "Falso":
                        expresion.add(elem);
                        break;
                    case "Cad":
                        expresion.add(elem);
                        break;
                    case "Suma":
                        expresion.add(elem);
                        break; 
                    case "Resta":
                        expresion.add(elem);
                        break; 
                    case "P_coma":
                        expresion.add(elem);
                        expresiones.add(validacionesAsig(expresion, parametros, variablesTabla, variables, counter));
                        asignacion = false;
                        expresion.clear();
                        System.out.println("Saliendo a evaluacion para expresion de ASIGNACION");
                        break;
               }
            } 
            else
            //Analisis de sentencia para bandera evalDes para decision
            if(evalDes){
                System.out.println("Entrando a evaluacion de DECISION");
                var = obj;
                switch(token){
                    case "operadorRelacional":
                        break;
                    case "Llave_a":
                        tipo = "Llave_a";
                        break;
                    case "Llave_c":
                        tipo = "Llave_c";
                        vartype1 = "";
                        vartype2 = "";
                        evalDes = false;
                        System.out.println("Saliendo a evaluacion de DECISION");
                        break;
                    case "Identificador":
                        if(temp.equals("Llave_a")){
                            asignacion = true;
                            expresion.add(elem);
                            System.out.println("CAMBIANDO a evaluacion de decision para ASIGNACION");
                        }else if(!variables.contains(var) && !parametros.contains(var)){
                           listModel.addElement("Error_Type::Semantico::\nDeclaracion. Linea: " + counter + ". La variable \"" + obj + "\" no ha sido declarada.");
                        } else if(vartype1.isEmpty()){
                            vartype1 = obtenerTipo(variablesTabla, var);
                        } else {
                            vartype2 = obtenerTipo(variablesTabla, var);
                            if(!vartype1.equals(vartype2))
                               listModel.addElement("Error_Type::Semantico::\nParametros de evaluacion. Linea: " + counter + ". La operacion de relacion no es posible con las variables otorgadas, verifique que sean del mismo tipo"); 
                        }
                        break;
                    
                                       
                    case "Verdad":
                        if(vartype1.isEmpty()) {
                            vartype1 = "Bool";
                        } else {
                            vartype2 = "Bool";
                            if(!vartype1.equals(vartype2)){
                                listModel.addElement("Error_Type::Semantico::\nasignacion de expresion. Linea: " + counter + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                            }
                            vartype1 = vartype2;
                        }
                        break;

                    case "Falso":
                        if(vartype1.isEmpty()) {
                            vartype1 = "Bool";
                        } else {
                            vartype2 = "Bool";
                            if(!vartype1.equals(vartype2)){
                              listModel.addElement("Error de asignacion de expresion. Linea: " + counter + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                            }
                            vartype1 = vartype2;
                        }
                        break;

                    case "Cad":
                        if(vartype1.isEmpty()) {
                            vartype1 = "Text";
                        } else {
                            vartype2 = "Text";
                            if(!vartype1.equals(vartype2)){
                               listModel.addElement("Error_Type::Semantico::\nasignacion de expresion. Linea: " + counter + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                            }
                            vartype1 = vartype2;
                        }
                        break;

                    }
            }
            else
            if(evalCic) {
                System.out.println("Entrando a evaluacion de CICLO");
                switch(token){
                    case "Mientras":
                        break;
                    case "operadorRelacional":
                        break;
                    case "Llave_a":
                        temp = "Llave_a";
                        cuerpo = true;
                        break;
                    case "Llave_c":
                        temp = "Llave_c";
                        evalCic = false;
                        cuerpo = false;
                        System.out.println("Saliendo de evaluacion de CICLO");
                        break;
                    case "Parentesis_a":
                        break;
                    case "Parentesis_c":
                        break;
                   
                    case "Identificador":
                        var = obj;
                        if(cuerpo){
                            asignacion = true;
                            expresion.add(elem);
                            tipo = temp;
                             System.out.println("CAMBIANDO a evaluacion de ciclo para ASIGNACION");
                        }else if(!variables.contains(var) && !parametros.contains(var)){
                           listModel.addElement("Error_Type::Semantico::\ndeclaracion. Linea: " + counter + ". La variable \"" + obj + "\" no ha sido declarada.");
                        } else if(vartype1.isEmpty()){
                            vartype1 = obtenerTipo(variablesTabla, var);
                            if(!vartype1.equals("Bool"))
                                listModel.addElement("Error semantico de ciclo. Linea: " + counter + ". La variable \"" + obj + "\" no es de tipo color.");
                        } else {
                            vartype2 = obtenerTipo(variablesTabla, var);
                            if(!vartype2.equals("color"))
                                listModel.addElement("Error semantico de ciclo. Linea: " + counter + ". La variable \"" + obj + "\" no es de tipo color.");
                            if(!vartype1.equals(vartype2))
                               listModel.addElement("Error semantico de parametros de evaluacion. Linea: " + counter + ". La operacion de relacion no es posible con las variables otorgadas, verifique que sean de tipo color"); 
                        }
                        break;
                    
                    case "Y_si":
                        vartype1 = "";
                        vartype2 = "";
                        evalDes = true;
                        break;
                }
            }else 
                
            if(evalFun) {
                System.out.println("Entrando a evaluacion de FUNCION");
                switch (token) {
                    case "Tarea":
                        tipo = token;
                        break;
                    case "Y_si":
                        tipo = token;
                        break;
                    case "Parentesis_a":
                        param = true;
                        break;
                    case "Parentesis_c":
                        param=false;
                        break;
                    case "Llave_a":
                        cuerpo = true;
                        break;
                    case "Igual":
                        expresion.add(elem);
                        break;
                    case "Identificador":
                        var = obj;
                        if("Tarea".equals(tipo)){ //Si es tipo funcion, comparara si el identificador no es una funcion ya repetida. 
                            if(funciones.contains(var)){
                               listModel.addElement("Error_Type::Semantico::\nasignacion. Linea: " + counter + ". La funcion \"" + var + "\" ya fue declarada.");
                            } else {
                                temp = var;
                                funciones.add(var);
                            }
                        } else if (param){ //Esto servira para evitar el contacto con los identificadores dentro de los parametros, proseguira con el analisis del curpo del ciclo al detectar si se encuentra con la llave de apertura {
                            if(variables.contains(var)){
                               listModel.addElement("Error_Type::Semantico::\nparametros. Linea: " + counter + ". El parametro \"" + var + "\" no puede nombrarse igual que una variable ya declarada.");
                            } else {
                                parametros.add(var);
                                variablesTabla.add(new Object[]{tipo, var});
                            }
                        } else if(cuerpo){
                            expresion.add(elem);
                            asignacion = true;
                        } 
                        break; 
                   
                    case "Mientras":
                        evalCic = true;
                        break;
                    
               }
                
                if(token.equals("Llave_c")){
                    evalFun = false;
                    cuerpo = false;
                    System.out.println("Saliendo a evaluacion de FUNCION");
                }
            }
            
        }
            mostrarResultados(variables, variablesTabla,funciones,parametros,expresiones,funcionesTabla);
    } 
   
    public void mostrarResultados(LinkedList<String> variables,LinkedList<Object[]> variablesTabla, LinkedList<String> funciones, LinkedList<String> parametros, LinkedList<String> expresion, LinkedList<Object[]> funcionesTabla){
        String tabla = "|-------------------------|";
        tabla = tabla +"\n|--------Variables--------|\n|-------------------------|";
        for (String var : variables) {
            tabla = tabla + "\n\t  " + var;
        }
        System.out.println(tabla);
        
        tabla = "|-------------------------|";
        tabla = tabla +"\n|-----Variables Tabla-----|\n|-------------------------|";
        for (Object[] var : variablesTabla) {
            tabla = tabla + "\n" + var[0] + "  -  " + var[1];
        }
        System.out.println(tabla);
        
        tabla = "|-------------------------|";
        tabla = tabla +"\n|--------Funciones--------|\n|-------------------------|";
        for (String fun : funciones) {
            tabla = tabla + "\n  " + fun;
        }
        System.out.println(tabla);
        
        tabla = "|-------------------------|";
        tabla = tabla +"\n|--------Parametros-------|\n|-------------------------|";
        for (String par : parametros) {
            tabla = tabla + "\n\t  " + par;
        }
        System.out.println(tabla);
        
        tabla = "|-------------------------|";
        tabla = tabla +"\n|-------Expresiones-------|\n|-------------------------|";
        for (String var : expresion) {
            tabla = tabla + "\n " + var;
        }
        System.out.println(tabla);
        
        tabla = "|-------------------------|";
        tabla = tabla +"\n|-----Funciones Tabla-----|\n|-------------------------|";
        for (Object[] fun : funcionesTabla) {
            tabla = tabla + "\n" + fun[0] + "()  -  retorno=" + fun[1] + "  -  tipo_retorno=" + fun[2];
        }
        System.out.println(tabla);
    }
   
     public String obtenerTipo(LinkedList<Object[]> list, String var){
        String tipo = "";
        for(Object[] elem: list){
            if(elem[1].equals(var)){
                tipo = elem[0].toString();
                break;
            } 
        }
        return tipo;
    }
    
    
    public String obtenerTipoFuncion(LinkedList<Object[]> list, String var){
        String tipo = "";
        for(Object[] elem: list){
            if(elem[0].equals(var)){
                tipo = elem[2].toString();
                break;
            } 
        }
        return tipo;
    }
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
    public static void agregarid(String ID, String tipo, String val) { 
       Arr.add(new ArryL(ID, tipo, val));
    }
   public static void setError(String error) {
        listModel.addElement(error);
    }
    public String validacionesAsig(LinkedList<Object[]> valores, LinkedList<String> parametros,LinkedList<Object[]> variablesTabla, LinkedList<String> variables, int renglon){
        String obj = "";
        String token = "";
        String principalType = "";
        Object[] temp = new Object[]{};
        Boolean bandera = true;
        Boolean estado = false;
        String vartype1 = "";
        String vartype2 = "";
        String expresion = "";
        
        System.out.println("Tamaño entrando a la evalucion de asignacion: " + valores.size());
        
        for(Object[] valor: valores){
            //System.out.println(valor[0] + " " + valor[1]); 
            
            //Evaluar si en caso de ser una variable existe como una declaracion o pertenecen al grupo de parametros

            //En caso de que la igualdad (lado derecho) sea del mismo tipo que del lado izquierdo a donde se le esta asignando el valor.
            //En caso de que haya sido declarada un identificador en el area de las expresiones verificar si ya esta declarada o pertenece a los parametros
            //Para esto tenemos distintos casos de forma en la que se puede generar una expresion
            //1.- identificador
            //2.- tipos (tiemp, veloc, cadena, energy, verdad, falso, colores)
            //3.- identificador (+-) identificador
            //4.- identificador (+-) tipos
            //5.- tipos (+-) identificador
            //6.- tipos (+-) tipos
            //7.- Cualquier de las operaciones anteriores de forma compleja de mas de dos operando e intercalando las operaciones de suma, resta, producto y diviision 
            
            //Una vez evaluamos que una expresion pueda ser correcta comparar que el lado izquierdo pertenece al mismo tipo que el lado derecho
            
            obj = valor[0].toString();
            token = valor[1].toString();
            //Ejemplo: token = identificador, obj = val1
            //System.out.println(obj);
            expresion = expresion + obj + " ";
            switch(token){
                case "identificador":
                    if(bandera){ //Esto guardara el primer identificador que corresponde al valor de comparacion de la expresion
                        temp = valor;
                        principalType = obtenerTipo(variablesTabla, temp[0].toString());
                    } else {
                        if(vartype1.isEmpty()) {
                            vartype1 = obtenerTipo(variablesTabla, obj);
                        } else {
                            vartype2 = obtenerTipo(variablesTabla, obj);
                            if(!vartype1.equals(vartype2)){
                              listModel.addElement("Error de expresion. Linea: " + renglon + ". Las variables u objetos no pertenecen al mismo tipo operacion incorrecta");
                                estado = true;
                            }
                        }
                    } 
                    if(!variables.contains(obj) && !parametros.contains(obj)){
                        listModel.addElement("Error de variable. Linea: " + renglon + ". La variable " + obj + " no ha sido declarada en el area de inicializacion o parametros.");
                    } 
                    break;
                    
                case "suma":
                    break;
                
                case "resta":
                    break;
                    
                case "producto":
                    break;
                    
                case "division":
                    break;
                    
                case "veloc":
                    if(vartype1.isEmpty()) {
                            vartype1 = "velocidad";
                        } else {
                            vartype2 = "velocidad";
                            if(!vartype1.equals(vartype2)){
                               listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                                estado = true;
                            }
                            vartype1 = vartype2;
                        }
                    break;
                    
                case "verdad":
                    if(vartype1.isEmpty()) {
                            vartype1 = "decision";
                        } else {
                           listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". Las operaciones entre valores de decision no son posibles"  );
                            estado = true;
                        }
                    break;
                
                case "falso":
                    if(vartype1.isEmpty()) {
                            vartype1 = "decision";
                        } else {
                          listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". Las operaciones entre valores de decision no son posibles"  );
                            estado = true;
                        }
                    break;
                    
                case "time":
                    if(vartype1.isEmpty()) {
                            vartype1 = "tiempo";
                        } else {
                            vartype2 = "tiempo";
                            if(!vartype1.equals(vartype2)){
                              listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                                estado = true;
                            }
                            vartype1 = vartype2;
                        }
                    break;
                    
                case "cadena":
                    if(vartype1.isEmpty()) {
                            vartype1 = "alerta";
                        } else {
                            vartype2 = "alerta";
                            if(!vartype1.equals(vartype2)){
                               listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                                estado = true;
                            }
                            vartype1 = vartype2;
                        }
                    break;
                    
                case "colores":
                    if(vartype1.isEmpty()) {
                        vartype1 = "color";
                    } else {
                        vartype2 = "color";
                        if(!vartype1.equals(vartype2)){
                           listModel.addElement("Error de asignacion de expresion. Linea: " + renglon + ". La variable \" \" de tipo " + vartype1 + " no corresponde a la variable \" \" de tipo " + vartype2  );
                            estado = true;
                        }
                        vartype1 = vartype2;
                    }
                    break;
                
                case "punto_medio":
                    if(valores.size()==4 && !principalType.equals(vartype1)){
                        listModel.addElement("Error1. Linea: " + renglon + ". Asignacion incorrecta de tipos el valor " + vartype1 + " no puede ser asignado a la variable principal " + temp[0].toString() + " de tipo " + principalType );                        
                    } else if (estado)
                       listModel.addElement("Error2. Linea: " + renglon + ". Asignacion incorrecta de tipos la expresion resultante no puede ser asignada a la variable principal " + temp[0].toString() + " de tipo " + principalType );                        
                    
                    break;
            }
            
            bandera = false;
        }
        
        return expresion;
        
    }
    
 public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
 public static boolean num(String c){

 
if (c.matches("[0-9]+[.][0-9]+|[0-9]+"))
return true;
else
return false ;
        }
 public static void Mostrar(String a) {
        String I = "";
        String t = "";
        String v = "";
        for (int i = 0; i < Arr.size(); i++) {
            if (Arr.get(i).getId().equals(a)) {

                I += Arr.get(i).getId();
                t += Arr.get(i).getTipo();
                v += Arr.get(i).getVal();
            }
        }
     //   JOptionPane.showMessageDialog(null, I + t + v);
    }
 public static String Buscar1(String a) {

        for (int i = 0; i < Arr.size(); i++) {
            if (Arr.get(i).getId().equals(a)) {
                return Arr.get(i).getVal();
            }

        }
        return "Error";
    }
 public static String Buscar(String a) {

        for (int i = 0; i < Arr.size(); i++) {
            if (Arr.get(i).getId().equals(a)) {

                return Arr.get(i).getTipo();
            }

        }
        return "Error";
    }
 public boolean existEnArray(String bus) {

        boolean saber = false;

        for (int i = 0; i <= Arr.size(); i++) {
            if (Arr.get(i).getId().equals(bus)) {
                saber = true;
                break;
            }
        }
        return saber;
    }
 public int indiceDato(String bus) {
        int j = 0;

        for (int i = 0; i < Arr.size(); i++) {
            if (Arr.get(i).getId().equals(bus)) {
                j = i;
                break;
            }
        }
        return j;
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
    private javax.swing.JButton jButton1;
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
