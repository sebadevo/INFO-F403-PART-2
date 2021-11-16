%%// Options of the scanner

%class Lexer	//Name
%unicode		//Use unicode
%line         	//Use line counter (yyline variable)
%column       	//Use character counter by line (yycolumn variable)
%type Symbol  //Says that the return type is Symbol
%standalone		//Standalone mode
%xstates YYINITIAL, CO_STATE, co_STATE

%function nextToken


// Return value of the program
%eofval{
	return new Symbol(LexicalUnit.END_OF_STREAM, yyline, yycolumn, yytext());
%eofval}


// Extended Regular Expressions
AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}
EndOfLine      = "\r"?"\n"

Integer        = (([1-9][0-9]*)|0)
Identifier     = {Alpha}{AlphaNumeric}*


%%// Identification of tokens

// Relational operators
"not"		    {return new Symbol(LexicalUnit.NOT,yyline, yycolumn,yytext());}
":="            {return new Symbol(LexicalUnit.ASSIGN,yyline, yycolumn,yytext());}
"="	            {return new Symbol(LexicalUnit.EQUAL,yyline, yycolumn,yytext());}
"-"             {return new Symbol(LexicalUnit.MINUS,yyline, yycolumn,yytext());}
"+"             {return new Symbol(LexicalUnit.PLUS,yyline, yycolumn,yytext());}
"*"             {return new Symbol(LexicalUnit.TIMES,yyline, yycolumn,yytext());}
":"             {return new Symbol(LexicalUnit.DIVIDE,yyline, yycolumn,yytext());}
">"		        {return new Symbol(LexicalUnit.GREATER,yyline, yycolumn,yytext());}
"<"		        {return new Symbol(LexicalUnit.SMALLER,yyline, yycolumn,yytext());}

// Others
"("             {return new Symbol(LexicalUnit.LPAREN,yyline, yycolumn,yytext());}
")"             {return new Symbol(LexicalUnit.RPAREN,yyline, yycolumn,yytext());}
";"             {return new Symbol(LexicalUnit.SEMICOLON,yyline, yycolumn,yytext());}

// If-for-while keywords
"if"	        {return new Symbol(LexicalUnit.IF,yyline, yycolumn,yytext());}
"then"          {return new Symbol(LexicalUnit.THEN,yyline, yycolumn,yytext());}
"endif"         {return new Symbol(LexicalUnit.ENDIF,yyline, yycolumn,yytext());}
"else"          {return new Symbol(LexicalUnit.ELSE,yyline, yycolumn,yytext());}
"while"         {return new Symbol(LexicalUnit.WHILE,yyline, yycolumn,yytext());}
"do"            {return new Symbol(LexicalUnit.DO,yyline, yycolumn,yytext());}
"endwhile"      {return new Symbol(LexicalUnit.ENDWHILE,yyline, yycolumn,yytext());}
"for"           {return new Symbol(LexicalUnit.FOR,yyline, yycolumn,yytext());}
"from"          {return new Symbol(LexicalUnit.FROM,yyline, yycolumn,yytext());}
"by"            {return new Symbol(LexicalUnit.BY,yyline, yycolumn,yytext());}
"to"            {return new Symbol(LexicalUnit.TO,yyline, yycolumn,yytext());}
"endfor"        {return new Symbol(LexicalUnit.ENDFOR,yyline, yycolumn,yytext());}
"print"         {return new Symbol(LexicalUnit.PRINT,yyline, yycolumn,yytext());}
"read"          {return new Symbol(LexicalUnit.READ,yyline, yycolumn,yytext());}
"end"           {return new Symbol(LexicalUnit.END,yyline, yycolumn,yytext());}	
"begin" 		{return new Symbol(LexicalUnit.BEG,yyline, yycolumn,yytext());}


// States 
<YYINITIAL> {
	"CO" {yybegin(CO_STATE);}
	"co" {yybegin(co_STATE);}
}

<CO_STATE> {
	[^"CO"] {}
	"CO" {yybegin(YYINITIAL);}
}

<co_STATE> {
	[^"\r"?"\n"] {}
	{EndOfLine} {yybegin(YYINITIAL);}
}

// VARNAME variable identifier
{Identifier}  {return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext());}

// NUMBER variable identifier
{Integer}  {return new Symbol(LexicalUnit.NUMBER,yyline, yycolumn,yytext());}

//Ignore other characters
. | {EndOfLine}            {}

