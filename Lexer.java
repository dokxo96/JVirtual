/* The following code was generated by JFlex 1.7.0 */

package codigo;
import static codigo.Tokens.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>C:/AnalizadorLexicoPrueba/src/codigo/Lexer.flex</tt>
 */
class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\5\1\7\1\7\1\6\22\0\1\3\1\70\1\10"+
    "\3\0\1\66\1\0\1\15\1\16\1\14\1\12\1\3\1\13\1\42"+
    "\1\4\12\2\1\0\1\23\1\41\1\11\1\71\2\0\1\32\1\53"+
    "\1\54\1\62\1\40\1\65\1\55\1\1\1\24\3\1\1\51\4\1"+
    "\1\43\1\1\1\34\1\1\1\63\2\1\1\46\1\1\1\21\1\0"+
    "\1\22\1\0\1\31\1\0\1\44\1\1\1\27\1\60\1\35\1\64"+
    "\2\1\1\26\2\1\1\45\1\52\1\25\1\30\1\33\1\57\1\50"+
    "\1\47\1\37\1\1\1\61\1\1\1\36\1\1\1\56\1\17\1\67"+
    "\1\20\7\0\1\7\u1fa2\0\1\7\1\7\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\4\2\1\23\1\24\11\2\2\1"+
    "\1\25\1\23\1\4\1\23\1\26\1\0\7\2\1\27"+
    "\11\2\1\25\1\0\7\2\1\30\5\2\1\31\4\2"+
    "\1\3\5\2\1\32\2\2\1\33\1\34\1\2\1\35"+
    "\10\2\1\36\1\2\1\37\6\2\1\40\3\2\1\41"+
    "\7\2\1\42\5\2\1\43\4\2\1\44\1\45\1\46"+
    "\5\2\1\47\1\2\1\50\1\2\1\51\5\2\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\72\0\164\0\256\0\350\0\u0122\0\72\0\72"+
    "\0\u015c\0\u0196\0\u01d0\0\72\0\u020a\0\72\0\72\0\72"+
    "\0\72\0\72\0\72\0\u0244\0\u027e\0\u02b8\0\u02f2\0\u032c"+
    "\0\72\0\u0366\0\u03a0\0\u03da\0\u0414\0\u044e\0\u0488\0\u04c2"+
    "\0\u04fc\0\u0536\0\u0570\0\u05aa\0\u015c\0\u015c\0\u05e4\0\72"+
    "\0\72\0\u061e\0\u0658\0\u0692\0\u06cc\0\u0706\0\u0740\0\u077a"+
    "\0\u07b4\0\72\0\u07ee\0\u0828\0\u0862\0\u089c\0\u08d6\0\u0910"+
    "\0\u094a\0\u0984\0\u09be\0\72\0\u09f8\0\u0a32\0\u0a6c\0\u0aa6"+
    "\0\u0ae0\0\u0b1a\0\u0b54\0\u0b8e\0\164\0\u0bc8\0\u0c02\0\u0c3c"+
    "\0\u0c76\0\u0cb0\0\164\0\u0cea\0\u0d24\0\u0d5e\0\u0d98\0\72"+
    "\0\u0dd2\0\u0e0c\0\u0e46\0\u0e80\0\u0eba\0\164\0\u0ef4\0\u0f2e"+
    "\0\164\0\164\0\u0f68\0\164\0\u0fa2\0\u0fdc\0\u1016\0\u1050"+
    "\0\u108a\0\u10c4\0\u10fe\0\u1138\0\164\0\u1172\0\164\0\u11ac"+
    "\0\u11e6\0\u1220\0\u125a\0\u1294\0\u12ce\0\164\0\u1308\0\u1342"+
    "\0\u137c\0\164\0\u13b6\0\u13f0\0\u142a\0\u1464\0\u149e\0\u14d8"+
    "\0\u1512\0\164\0\u154c\0\u1586\0\u15c0\0\u15fa\0\u1634\0\164"+
    "\0\u166e\0\u16a8\0\u16e2\0\u171c\0\164\0\164\0\164\0\u1756"+
    "\0\u1790\0\u17ca\0\u1804\0\u183e\0\164\0\u1878\0\164\0\u18b2"+
    "\0\164\0\u18ec\0\u1926\0\u1960\0\u199a\0\u19d4\0\164";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\5\1\0"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\5\3\1\25\1\3"+
    "\1\26\3\3\1\27\1\30\1\31\1\32\2\3\1\33"+
    "\2\3\1\34\1\3\1\35\1\36\1\37\4\3\1\40"+
    "\1\41\1\3\1\42\1\43\1\44\1\45\1\46\73\0"+
    "\2\3\21\0\15\3\2\0\23\3\6\0\1\4\72\0"+
    "\1\5\2\0\1\5\67\0\1\47\76\0\1\50\72\0"+
    "\1\51\72\0\1\51\71\0\1\52\57\0\2\3\21\0"+
    "\1\3\1\53\13\3\2\0\7\3\1\54\13\3\5\0"+
    "\2\3\21\0\15\3\2\0\15\3\1\55\1\56\4\3"+
    "\5\0\2\3\21\0\11\3\1\57\3\3\2\0\1\3"+
    "\1\60\21\3\5\0\2\3\21\0\1\3\1\61\13\3"+
    "\2\0\23\3\15\0\1\50\1\0\1\62\57\0\2\3"+
    "\21\0\11\3\1\63\3\3\2\0\23\3\5\0\2\3"+
    "\21\0\5\3\1\64\7\3\2\0\23\3\5\0\2\3"+
    "\21\0\2\3\1\65\12\3\2\0\23\3\5\0\2\3"+
    "\21\0\4\3\1\66\10\3\2\0\23\3\5\0\2\3"+
    "\21\0\15\3\2\0\1\3\1\67\21\3\5\0\2\3"+
    "\21\0\2\3\1\70\12\3\2\0\23\3\5\0\2\3"+
    "\21\0\11\3\1\71\3\3\2\0\23\3\5\0\2\3"+
    "\21\0\11\3\1\72\3\3\2\0\23\3\5\0\2\3"+
    "\21\0\15\3\2\0\1\3\1\73\21\3\72\0\1\74"+
    "\72\0\1\74\2\0\5\47\3\0\62\47\2\0\1\75"+
    "\70\0\2\3\21\0\2\3\1\76\12\3\2\0\23\3"+
    "\5\0\2\3\21\0\7\3\1\77\5\3\2\0\23\3"+
    "\5\0\2\3\21\0\15\3\2\0\16\3\1\100\4\3"+
    "\5\0\2\3\21\0\2\3\1\101\12\3\2\0\1\3"+
    "\1\102\21\3\5\0\2\3\21\0\12\3\1\103\2\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\5\3"+
    "\1\104\15\3\5\0\2\3\21\0\13\3\1\105\1\3"+
    "\2\0\23\3\5\0\2\3\21\0\13\3\1\106\1\3"+
    "\2\0\1\3\1\107\21\3\5\0\2\3\21\0\15\3"+
    "\2\0\4\3\1\110\16\3\5\0\2\3\21\0\11\3"+
    "\1\111\3\3\2\0\23\3\5\0\2\3\21\0\4\3"+
    "\1\112\10\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\113\15\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\114\15\3\5\0\2\3\21\0\13\3"+
    "\1\115\1\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\116\15\3\5\0\2\3\21\0\15\3"+
    "\2\0\2\3\1\117\20\3\6\0\1\75\13\0\1\120"+
    "\54\0\2\3\21\0\3\3\1\121\11\3\2\0\23\3"+
    "\5\0\2\3\21\0\15\3\2\0\5\3\1\122\15\3"+
    "\5\0\2\3\21\0\11\3\1\123\3\3\2\0\23\3"+
    "\5\0\2\3\21\0\15\3\2\0\4\3\1\124\16\3"+
    "\5\0\2\3\21\0\1\3\1\125\13\3\2\0\23\3"+
    "\5\0\2\3\21\0\13\3\1\126\1\3\2\0\23\3"+
    "\5\0\2\3\21\0\11\3\1\127\3\3\2\0\23\3"+
    "\5\0\2\3\21\0\15\3\2\0\5\3\1\130\15\3"+
    "\5\0\2\3\21\0\15\3\2\0\2\3\1\131\20\3"+
    "\5\0\2\3\21\0\2\3\1\132\12\3\2\0\23\3"+
    "\5\0\2\3\21\0\1\3\1\133\13\3\2\0\23\3"+
    "\5\0\2\3\21\0\15\3\2\0\2\3\1\134\20\3"+
    "\5\0\2\3\21\0\15\3\2\0\1\3\1\135\21\3"+
    "\5\0\2\3\21\0\11\3\1\136\3\3\2\0\23\3"+
    "\5\0\2\3\21\0\2\3\1\137\12\3\2\0\15\3"+
    "\1\140\5\3\5\0\2\3\21\0\15\3\2\0\4\3"+
    "\1\141\16\3\5\0\2\3\21\0\2\3\1\142\12\3"+
    "\2\0\23\3\5\0\2\3\21\0\2\3\1\143\12\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\5\3"+
    "\1\144\15\3\5\0\2\3\21\0\4\3\1\145\10\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\13\3"+
    "\1\146\7\3\5\0\2\3\21\0\15\3\2\0\1\3"+
    "\1\147\21\3\5\0\2\3\21\0\4\3\1\150\10\3"+
    "\2\0\23\3\5\0\2\3\21\0\13\3\1\151\1\3"+
    "\2\0\23\3\5\0\2\3\21\0\5\3\1\152\7\3"+
    "\2\0\23\3\5\0\2\3\21\0\1\3\1\153\13\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\21\3"+
    "\1\154\1\3\5\0\2\3\21\0\15\3\2\0\1\3"+
    "\1\155\21\3\5\0\2\3\21\0\4\3\1\156\10\3"+
    "\2\0\23\3\5\0\2\3\21\0\4\3\1\157\10\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\7\3"+
    "\1\160\13\3\5\0\2\3\21\0\13\3\1\161\1\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\1\3"+
    "\1\162\21\3\5\0\2\3\21\0\3\3\1\163\11\3"+
    "\2\0\23\3\5\0\2\3\21\0\15\3\2\0\5\3"+
    "\1\164\15\3\5\0\2\3\21\0\2\3\1\165\12\3"+
    "\2\0\15\3\1\166\5\3\5\0\2\3\21\0\11\3"+
    "\1\167\3\3\2\0\23\3\5\0\2\3\21\0\2\3"+
    "\1\170\12\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\15\3\1\156\5\3\5\0\2\3\21\0\5\3"+
    "\1\171\7\3\2\0\23\3\5\0\2\3\21\0\11\3"+
    "\1\172\3\3\2\0\23\3\5\0\2\3\21\0\11\3"+
    "\1\173\3\3\2\0\23\3\5\0\2\3\21\0\11\3"+
    "\1\174\3\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\1\3\1\175\21\3\5\0\2\3\21\0\15\3"+
    "\2\0\13\3\1\176\7\3\5\0\2\3\21\0\11\3"+
    "\1\177\3\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\200\15\3\5\0\2\3\21\0\3\3"+
    "\1\201\11\3\2\0\23\3\5\0\2\3\21\0\6\3"+
    "\1\202\6\3\2\0\23\3\5\0\2\3\21\0\1\3"+
    "\1\203\13\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\15\3\1\204\5\3\5\0\2\3\21\0\15\3"+
    "\2\0\4\3\1\205\16\3\5\0\2\3\21\0\15\3"+
    "\2\0\14\3\1\206\6\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\207\15\3\5\0\2\3\21\0\15\3"+
    "\2\0\1\3\1\210\21\3\5\0\2\3\21\0\7\3"+
    "\1\211\5\3\2\0\23\3\5\0\2\3\21\0\3\3"+
    "\1\212\11\3\2\0\23\3\5\0\2\3\21\0\11\3"+
    "\1\213\3\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\214\15\3\5\0\2\3\21\0\7\3"+
    "\1\215\5\3\2\0\23\3\5\0\2\3\21\0\2\3"+
    "\1\216\12\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\217\15\3\5\0\2\3\21\0\15\3"+
    "\2\0\10\3\1\220\12\3\5\0\2\3\21\0\15\3"+
    "\2\0\1\3\1\221\21\3\5\0\2\3\21\0\15\3"+
    "\2\0\1\3\1\222\21\3\5\0\2\3\21\0\13\3"+
    "\1\223\1\3\2\0\23\3\5\0\2\3\21\0\11\3"+
    "\1\224\3\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\5\3\1\225\15\3\5\0\2\3\21\0\2\3"+
    "\1\226\12\3\2\0\23\3\5\0\2\3\21\0\15\3"+
    "\2\0\1\3\1\227\21\3\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6670];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\4\1\2\11\3\1\1\11\1\1\6\11"+
    "\5\1\1\11\16\1\2\11\1\0\7\1\1\11\11\1"+
    "\1\11\1\0\22\1\1\11\107\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    public String lexeme;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 206) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Tokens yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return ERROR;
            } 
            // fall through
          case 43: break;
          case 2: 
            { lexeme=yytext(); return Identificador;
            } 
            // fall through
          case 44: break;
          case 3: 
            { lexeme=yytext(); return Numero;
            } 
            // fall through
          case 45: break;
          case 4: 
            { /*Ignore*/
            } 
            // fall through
          case 46: break;
          case 5: 
            { lexeme=yytext(); return Division;
            } 
            // fall through
          case 47: break;
          case 6: 
            { return Linea;
            } 
            // fall through
          case 48: break;
          case 7: 
            { lexeme=yytext(); return Comillas;
            } 
            // fall through
          case 49: break;
          case 8: 
            { lexeme=yytext(); return Igual;
            } 
            // fall through
          case 50: break;
          case 9: 
            { lexeme=yytext(); return Suma;
            } 
            // fall through
          case 51: break;
          case 10: 
            { lexeme=yytext(); return Resta;
            } 
            // fall through
          case 52: break;
          case 11: 
            { lexeme=yytext(); return Multiplicacion;
            } 
            // fall through
          case 53: break;
          case 12: 
            { lexeme=yytext(); return Parentesis_a;
            } 
            // fall through
          case 54: break;
          case 13: 
            { lexeme=yytext(); return Parentesis_c;
            } 
            // fall through
          case 55: break;
          case 14: 
            { lexeme=yytext(); return Llave_a;
            } 
            // fall through
          case 56: break;
          case 15: 
            { lexeme=yytext(); return Llave_c;
            } 
            // fall through
          case 57: break;
          case 16: 
            { lexeme = yytext(); return Corchete_a;
            } 
            // fall through
          case 58: break;
          case 17: 
            { lexeme = yytext(); return Corchete_c;
            } 
            // fall through
          case 59: break;
          case 18: 
            { lexeme=yytext(); return P_coma;
            } 
            // fall through
          case 60: break;
          case 19: 
            { lexeme = yytext(); return operadorRelacional ;
            } 
            // fall through
          case 61: break;
          case 20: 
            { lexeme=yytext(); return punto;
            } 
            // fall through
          case 62: break;
          case 21: 
            { lexeme=yytext(); return operadorLogico;
            } 
            // fall through
          case 63: break;
          case 22: 
            { lexeme = yytext(); return operadorIncrementoDecremento;
            } 
            // fall through
          case 64: break;
          case 23: 
            { lexeme=yytext(); return asignacion;
            } 
            // fall through
          case 65: break;
          case 24: 
            { lexeme=yytext(); return Ent;
            } 
            // fall through
          case 66: break;
          case 25: 
            { lexeme=yytext(); return Car;
            } 
            // fall through
          case 67: break;
          case 26: 
            { lexeme=yytext(); return Text;
            } 
            // fall through
          case 68: break;
          case 27: 
            { lexeme=yytext(); return Real;
            } 
            // fall through
          case 69: break;
          case 28: 
            { lexeme=yytext(); return Y_si;
            } 
            // fall through
          case 70: break;
          case 29: 
            { lexeme=yytext(); return Bool;
            } 
            // fall through
          case 71: break;
          case 30: 
            { lexeme=yytext(); return Aviso;
            } 
            // fall through
          case 72: break;
          case 31: 
            { lexeme=yytext(); return Tarea;
            } 
            // fall through
          case 73: break;
          case 32: 
            { lexeme=yytext(); return operadorBooleano;
            } 
            // fall through
          case 74: break;
          case 33: 
            { lexeme=yytext(); return Avanza;
            } 
            // fall through
          case 75: break;
          case 34: 
            { lexeme=yytext(); return Imprime;
            } 
            // fall through
          case 76: break;
          case 35: 
            { lexeme=yytext(); return Detener;
            } 
            // fall through
          case 77: break;
          case 36: 
            { lexeme=yytext(); return Mientras;
            } 
            // fall through
          case 78: break;
          case 37: 
            { lexeme=yytext(); return Gira_izq;
            } 
            // fall through
          case 79: break;
          case 38: 
            { lexeme=yytext(); return Gira_der;
            } 
            // fall through
          case 80: break;
          case 39: 
            { lexeme=yytext(); return Inicio_App;
            } 
            // fall through
          case 81: break;
          case 40: 
            { lexeme=yytext(); return Retroceder;
            } 
            // fall through
          case 82: break;
          case 41: 
            { lexeme=yytext(); return Advertencia;
            } 
            // fall through
          case 83: break;
          case 42: 
            { lexeme=yytext(); return VerificarBateria;
            } 
            // fall through
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
