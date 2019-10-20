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

/* Operador Igual */
( "=" ) {lexeme=yytext(); return Igual;}

/* Operador Suma */
( "+" ) {lexeme=yytext(); return Suma;}

/* Operador Resta */
( "-" ) {lexeme=yytext(); return Resta;}

/* Operador Multiplicacion */
( "*" ) {lexeme=yytext(); return Multiplicacion;}

/* Operador Division */
( "/" ) {lexeme=yytext(); return Division;}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexeme = yytext(); return Op_atribucion;}

/*Operadores Booleanos*/
(true | false)      {lexeme = yytext(); return Op_booleano;}

/* Parentesis de apertura */
( "(" ) {lexeme=yytext(); return Parentesis_a;}

/* Parentesis de cierre */
( ")" ) {lexeme=yytext(); return Parentesis_c;}

/* Llave de apertura */
( "{" ) {lexeme=yytext(); return Llave_a;}

/* Llave de cierre */
( "}" ) {lexeme=yytext(); return Llave_c;}

/* Corchete de apertura */
( "[" ) {lexeme = yytext(); return Corchete_a;}

/* Corchete de cierre */
( "]" ) {lexeme = yytext(); return Corchete_c;}


/* Punto y coma */
( ";" ) {lexeme=yytext(); return P_coma;}





(Inicio_App) {lexeme=yytext(); return Inicio_App;}
(Text)       {lexeme=yytext(); return Text;}
(Ent)        {lexeme=yytext(); return Ent;}
("<-")       {lexeme=yytext(); return asignacion;}
(".")        {lexeme=yytext(); return punto;}
(Real)       {lexeme=yytext(); return Real;}

(Bool)          {lexeme=yytext(); return Bool;}
(Car)           {lexeme=yytext(); return Car;}
(Verdad|Falso)  {lexeme=yytext(); return operadorBooleano;}
("++"|"--")     {lexeme = yytext(); return operadorIncrementoDecremento;}
("&&"|"||"|"!") {lexeme=yytext(); return operadorLogico;}
(">"|"<"|"=="|"!="|">="|"<=") {lexeme = yytext(); return operadorRelacional ;}
(Y_si) {lexeme=yytext(); return Y_si;}
(Tarea) {lexeme=yytext(); return Tarea;}
(Mientras) {lexeme=yytext(); return Mientras;}


/* Identificador */
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/* Numero */
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}

/* Error de analisis */
 . {return ERROR;}
