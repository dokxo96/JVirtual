package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}

/* Comillas */
( "\"" ) {lexeme=yytext(); return Comillas;}



( "=" ) {lexeme=yytext(); return Igual;}
( "+" ) {lexeme=yytext(); return Suma;}
( "-" ) {lexeme=yytext(); return Resta;}
( "*" ) {lexeme=yytext(); return Multiplicacion;}
( "/" ) {lexeme=yytext(); return Division;}
( "(" ) {lexeme=yytext(); return Parentesis_a;}
( ")" ) {lexeme=yytext(); return Parentesis_c;}
( "{" ) {lexeme=yytext(); return Llave_a;}
( "}" ) {lexeme=yytext(); return Llave_c;}
( "[" ) {lexeme = yytext(); return Corchete_a;}
( "]" ) {lexeme = yytext(); return Corchete_c;}
( ";" ) {lexeme=yytext(); return P_coma;}


(Inicio_App) {lexeme=yytext(); return Inicio_App;}
(Text)       {lexeme=yytext(); return Text;}
(Ent)        {lexeme=yytext(); return Ent;}
("<-")       {lexeme=yytext(); return asignacion;}
(".")        {lexeme=yytext(); return punto;}
(Real)       {lexeme=yytext(); return Real;}
(Y_si)       {lexeme=yytext(); return Y_si;}
(Tarea)      {lexeme=yytext(); return Tarea;}
(Mientras)   {lexeme=yytext(); return Mientras;}
(Imprime)    {lexeme=yytext(); return Imprime;}
(Bool)       {lexeme=yytext(); return Bool;}
(Car)        {lexeme=yytext(); return Car;}
(Gira_izq)   {lexeme=yytext(); return Gira_izq;}
(Gira_der)   {lexeme=yytext(); return Gira_der;}
(Avanza)     {lexeme=yytext(); return Avanza;}
(Retroceder) {lexeme=yytext(); return Retroceder;}
(Detener)    {lexeme=yytext(); return Detener;}
(Aviso)      {lexeme=yytext(); return Aviso;}
(Advertencia) {lexeme=yytext(); return Advertencia;}
(VerificarBateria) {lexeme=yytext(); return VerificarBateria;}


(Verdad|Falso)  {lexeme=yytext(); return operadorBooleano;}
("++"|"--")     {lexeme = yytext(); return operadorIncrementoDecremento;}
("&&"|"||"|"!") {lexeme=yytext(); return operadorLogico;}


(">"|"<"|"=="|"!="|">="|"<=") {lexeme = yytext(); return operadorRelacional ;}


{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {lexeme=yytext(); return ERROR;}