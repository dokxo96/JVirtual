package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r, \n]+
%{
    private Symbol symbol (int type, Object value){
        return new Symbol (type, yyline, yycolumn, value);       
    }
    
    private Symbol symbol (int type){
        return new Symbol (type, yyline, yycolumn);       
    }
%}
%%

Inicio_App  {return new Symbol (sym.Inicio_App, yychar, yyline,yytext());}
Tarea       {return new Symbol (sym.Tarea, yychar, yyline, yytext());}
Durante     {return new Symbol (sym.Durante, yychar, yyline, yytext());}
Repite      {return new Symbol (sym.Repite, yychar, yyline, yytext());}
Y_si        {return new Symbol (sym.Y_si, yychar, yyline, yytext());}
Imprime     {return new Symbol (sym.Imprime, yychar, yyline, yytext());}
Ingresa     {return new Symbol (sym.Ingresa, yychar, yyline, yytext());}
Text        {return new Symbol (sym.Text, yychar, yyline, yytext())}
Inc         {return new Symbol (sym.Inc, yychar, yyline, yytext());}
Dec         {return new Symbol (sym.Dec, yychar, yyline, yytext());}
Publica     {return new Symbol (sym.Publica, yychar, yyline, yytext());}
Ent         {return new Symbol (sym.Ent, yychar, yyline, yytext());}
Real        {return new Symbol (sym.Real, yychar, yyline, yytext());}
RealExt     {return new Symbol (sym.RealExt, yychar, yyline, yytext());}
Bool        {return new Symbol (sym.Bool, yychar, yyline, yytext());}
Car         {return new Symbol (sym.Car, yychar, yyline, yytext());}
Vibrar      {return new Symbol (sym.Vibrar, yychar, yyline, yytext())}
Ir          {return new Symbol (sym.Ir, yychar, yyline, yytext());}
Funcion     {return new Symbol (sym.Funcion, yychar, yyline, yytext());}
Girar_Iz    {return new Symbol (sym.Girar_Iz, yychar, yyline, yytext());}
Girar_De    {return new Symbol (sym.Girar_De, yychar, yyline, yytext());}
Avanza      {return new Symbol (sym.Avanza, yychar, yyline, yytext());}
Alto        {return new Symbol (sym.Alto, yychar, yyline, yytext());}

{espacio} {/*Ignore*/}

"//".* {/*Ignore*/}

"=" {return new Symbol (sym.Igual, yychar, yyline, yytext());}

"<" {return new Symbol (sym.Menor, yychar, yyline, yytext());}

">" {return new Symbol (sym.Mayor, yychar, yyline, yytext());}

"+" {return new Symbol (sym.Mas, yychar, yyline, yytext());}
"-" {return new Symbol (sym.Resta, yychar, yyline, yytext());}
"*" {return new Symbol (sym.Multiplicacion, yychar, yyline, yytext());}
"/" {return new Symbol (sym.Division, yychar, yyline, yytext());}
"^" {return new Symbol (sym.Potencia, yychar, yyline, yytext());}
";" {return new Symbol (sym.PuntoComa, yychar, yyline, yytext());}
"{" {return new Symbol (sym.llaveApertura, yychar, yyline, yytext());}
"}" {return new Symbol (sym.llaveCierre, yychar, yyline, yytext());}
"(" {return new Symbol (sym.ParentesisApertura, yychar, yyline, yytext());}
")" {return new Symbol (sym.ParentesisCierre, yychar, yyline, yytext());}


{L}({L}|{D})*      {return new Symbol (sym.Identificador, yychar, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol (sym.Numero, yychar, yyline, yytext());}
 .                 {return new Symbol (sym.ERROR, yychar, yyline, yytext());}