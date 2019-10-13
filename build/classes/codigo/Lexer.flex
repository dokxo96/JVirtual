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

Inicio_App  {lexeme=yytext(); return Inicio_App;}
Tarea       {lexeme=yytext(); return Tarea;}
Durante     {lexeme=yytext(); return Durante;}
Repite      {lexeme=yytext(); return Repite;}
Y_si        {lexeme=yytext(); return Y_si;}
Imprime     {lexeme=yytext(); return Imprime;}
Ingresa     {lexeme=yytext(); return Ingresa;}
Text        {lexeme=yytext(); return Text;}
Inc         {lexeme=yytext(); return Inc;}
Dec         {lexeme=yytext(); return Dec;}
Publica     {lexeme=yytext(); return Publica;}
Ent         {lexeme=yytext(); return Ent;}
Real        {lexeme=yytext(); return Real;}
RealExt     {lexeme=yytext(); return RealExt;}
Bool        {lexeme=yytext(); return Bool;}
Car         {lexeme=yytext(); return Car;}
Vibrar      {lexeme=yytext(); return Vibrar;}
Ir          {lexeme=yytext(); return Ir;}
Funcion     {lexeme=yytext(); return Funcion;}
Girar_Iz    {lexeme=yytext(); return Girar_Iz;}
Girar_De    {lexeme=yytext(); return Girar_De;}
Avanza      {lexeme=yytext(); return Avanza;}
Alto        {lexeme=yytext(); return Alto;}

{espacio} {/*Ignore*/}

"//".* {/*Ignore*/}

"\n" {lexeme=yytext(); return saltoLinea;}
"=" {lexeme=yytext(); return Igual;}

"<" {lexeme=yytext();return Menor;}

">" {lexeme=yytext(); return Mayor}

"+" {lexeme=yytext(); return Mas;}
"-" {lexeme=yytext(); return Resta;}
"*" {lexeme=yytext(); return Multiplicacion;}
"/" {lexeme=yytext(); return Division;}
"^" {lexeme=yytext(); return Potencia;}
";" {lexeme=yytext(); return PuntoComa;}
"{" {lexeme=yytext(); return llaveApertura;}
"}" {lexeme=yytext(); return llaveCierre;}
"(" {lexeme=yytext(); return ParentesisApertura;}
")" {lexeme=yytext(); return ParentesisCierre;}



{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {lexeme=yytext(); return ERROR;}