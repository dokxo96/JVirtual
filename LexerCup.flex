package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
%column
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }        

%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operador Suma */
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}

/* Operador Resta */
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}

/* Operador Multiplicacion */
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

/* Operador Division */
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return new Symbol(sym.Op_atribucion, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}



(Inicio_App) {return new Symbol (sym.Inicio_App, yychar, yyline, yytext());}
(Text)       {return new Symbol (sym.Text,       yychar, yyline, yytext());}
(Ent)        {return new Symbol (sym.Ent,        yychar, yyline, yytext());}
("<-")       {return new Symbol (sym.asignacion, yychar, yyline, yytext());}
(".")        {return new Symbol (sym.punto,      yychar, yyline, yytext());}
(Real)       {return new Symbol (sym.Real,       yychar, yyline, yytext());}

(Bool)       {return new Symbol (sym.Bool, yychar, yyline, yytext());}
(Car)        {return new Symbol (sym.Car, yychar, yyline, yytext());}
(Verdad|Falso)  {return new Symbol(sym.operadorBooleano, yychar, yyline, yytext());}
( "++" | "--" ) {return new Symbol(sym.operadorIncrementoDecremento, yychar, yyline, yytext());}
("&&"|"||"|"!") {return new Symbol(sym.operadorLogico, yychar, yyline, yytext());}
(">"|"<"|"=="|"!="|">=") {return new Symbol (sym.operadorRelacional, yychar, yyline, yytext());}
(Y_si) {return new Symbol (sym.Y_si, yychar, yyline, yytext());}
(Tarea) {return new Symbol (sym.Tarea, yychar, yyline, yytext());}
(Mientras) {return new Symbol (sym.Mientras, yychar, yyline, yytext());}
(Imprime) {return new Symbol (sym.Imprime, yychar, yyline, yytext());}


/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yycolumn, yyline, yytext());}
