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

Y_si  {lexeme=yytext(); return Y_si;}
Text  {lexeme=yytext(); return Text;}
Inicio_App  {lexeme=yytext(); return Inicio_App;}
Bool  {lexeme=yytext(); return Bool;}
{espacio} {/*Ignore*/}

"//".* {/*Ignore*/}

"\n" {lexeme=yytext(); return saltoLinea;}
"=" {lexeme=yytext(); return Igual;}
"+" {lexeme=yytext(); return Mas;}
"-" {lexeme=yytext(); return Resta;}
"*" {lexeme=yytext(); return Multiplicacion;}
"/" {lexeme=yytext(); return Division;}
";" {lexeme=yytext(); return PuntoComa;}
"{" {lexeme=yytext(); return llaveApertura;}
"}" {lexeme=yytext(); return llaveCierre;}


{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {lexeme=yytext(); return ERROR;}
