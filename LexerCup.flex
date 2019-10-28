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


( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}


(Inicio_App) {return new Symbol (sym.Inicio_App, yychar, yyline, yytext());}
(Text)       {return new Symbol (sym.Text,       yychar, yyline, yytext());}
(Ent)        {return new Symbol (sym.Ent,        yychar, yyline, yytext());}
("<-")       {return new Symbol (sym.asignacion, yychar, yyline, yytext());}
(".")        {return new Symbol (sym.punto,      yychar, yyline, yytext());}
(Real)       {return new Symbol (sym.Real,       yychar, yyline, yytext());}
(Y_si)       {return new Symbol (sym.Y_si, yychar, yyline, yytext());}
(Tarea)      {return new Symbol (sym.Tarea, yychar, yyline, yytext());}
(Mientras)   {return new Symbol (sym.Mientras, yychar, yyline, yytext());}
(Imprime)    {return new Symbol (sym.Imprime, yychar, yyline, yytext());}
(Bool)       {return new Symbol (sym.Bool, yychar, yyline, yytext());}
(Car)        {return new Symbol (sym.Car, yychar, yyline, yytext());}

(Gira_izq)   {return new Symbol (sym.Gira_izq, yychar, yyline, yytext());}
(Gira_der)   {return new Symbol (sym.Gira_der, yychar, yyline, yytext());}
(Avanza)     {return new Symbol (sym.Avanza, yychar, yyline, yytext());}
(Retroceder) {return new Symbol (sym.Retroceder, yychar, yyline, yytext());}
(Detener)    {return new Symbol (sym.Detener, yychar, yyline, yytext());}
(Aviso)      {return new Symbol (sym.Aviso, yychar, yyline, yytext());}
(Advertencia) {return new Symbol (sym.Advertencia, yychar, yyline, yytext());}
(VerificarBateria) {return new Symbol (sym. VerificarBateria, yychar, yyline, yytext());}


(Verdad|Falso)  {return new Symbol(sym.operadorBooleano, yychar, yyline, yytext());}
( "++" | "--" ) {return new Symbol(sym.operadorIncrementoDecremento, yychar, yyline, yytext());}
("&&"|"||"|"!") {return new Symbol(sym.operadorLogico, yychar, yyline, yytext());}


(">"|"<"|"=="|"!="|">=") {return new Symbol (sym.operadorRelacional, yychar, yyline, yytext());}



/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yycolumn, yyline, yytext());}