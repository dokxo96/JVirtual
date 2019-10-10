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
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol (int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    
    private Symbol symbol (int type){
        return new Symbol(type,yyline,yycolumn);
    }
%}
%%

Y_si  {return new Symbol(sym.Y_si, yychar, yyline, yytext());}
Text {return new Symbol(sym.Text, yychar, yyline, yytext());}
Inicio_App  {return new Symbol(sym.Inicio_App, yychar, yyline, yytext());}
Bool {return new Symbol(sym.Bool, yychar, yyline, yytext());}
{espacio} {/*Ignore*/}

"//".* {/*Ignore*/}

"=" {return new Symbol(sym.Igual, yychar, yyline, yytext());}
"+" {return new Symbol(sym.Mas, yychar, yyline, yytext());}
"-" {return new Symbol(sym.Resta, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
"/" {return new Symbol(sym.Division, yychar, yyline, yytext());}
";" {return new Symbol(sym.PuntoComa, yychar, yyline, yytext());}
"{" {return new Symbol(sym.llaveApertura, yychar, yyline, yytext());}
"}" {return new Symbol(sym.llaveCierre, yychar, yyline, yytext());}


{L}({L}|{D})*  {return new Symbol(sym.Identificador, yychar, yyline, yytext());} 
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());} 
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());} 

