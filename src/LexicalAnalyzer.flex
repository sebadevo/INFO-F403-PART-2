import java.util.regex.PatternSyntaxException;

%%// Options of the scanner

%class Lexer// Name
%unicode               // Use unicode
%line                  // Use line counter (yyline variable)
%column                // Use character counter by line (yycolumn variable)
%function nextToken
%type Symbol
%yylexthrow PatternSyntaxException

%eofval{
	return new Symbol(LexicalUnit.END_OF_STREAM, yyline, yycolumn);
%eofval}

//Extended Regular Expressions

AlphaUpperCase    = [A-Z]
AlphaLowerCase    = [a-z]
Alpha             = {AlphaUpperCase}|{AlphaLowerCase}
Numeric           = [0-9]
AlphaNumeric      = {Alpha}|{Numeric}
LowerAlphaNumeric	= {AlphaLowerCase}|{Numeric}

BadInteger     = (0[0-9]+)
Integer        = ([1-9][0-9]*)|0
VarName        = ({Alpha})({AlphaNumeric})*
LineFeed       = "\n"
CarriageReturn = "\r"
EndLine        = ({LineFeed}{CarriageReturn}?) | ({CarriageReturn}{LineFeed}?)
Space          = (\t | \f | " ")
Spaces         = {Space}+
Separator		= ({Space}) | ({EndLine})
Any                   = ([^"\n""\r"])*
UpToEnd		= ({Any}{EndLine}) | ({EndLine})

//Declare exclusive states
%xstate YYINITIAL, SHORTCOMMENTS, LONGCOMMENTS

%%// Identification of tokens


<LONGCOMMENTS> {
// End of comment
	"CO"			  {yybegin(YYINITIAL);} // go back to analysis
  <<EOF>>             {throw new PatternSyntaxException("A comment is never closed.",yytext(),yyline);}
	[^]				  {} //ignore any character
}

<YYINITIAL> {
// Comments
    "CO"              {yybegin(LONGCOMMENTS);} // go to ignore mode
    "co"{UpToEnd}     {} // go to ignore mode
// Code delimiters
  "begin"         	  {return new Symbol(LexicalUnit.BEG, yyline, yycolumn, yytext());}
  "end"           	  {return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext());}
  ";"                 {return new Symbol(LexicalUnit.SEMICOLON, yyline, yycolumn, yytext());}
// Assignation
  ":="                {return new Symbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext());}
// Parenthesis
  "("                 {return new Symbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext());}
  ")"                 {return new Symbol(LexicalUnit.RPAREN
, yyline, yycolumn, yytext());}
// Arithmetic signs
  "+"                 {return new Symbol(LexicalUnit.PLUS, yyline, yycolumn, yytext());}
  "-"                 {return new Symbol(LexicalUnit.MINUS, yyline, yycolumn, yytext());}
  "*"                 {return new Symbol(LexicalUnit.TIMES, yyline, yycolumn, yytext());}
  "/"                 {return new Symbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());}
// Negation
  "not"               {return new Symbol(LexicalUnit.NOT, yyline, yycolumn, yytext());}
// Conditional keywords
  "if"                {return new Symbol(LexicalUnit.IF, yyline, yycolumn, yytext());}
  "then"              {return new Symbol(LexicalUnit.THEN, yyline, yycolumn, yytext());}
  "else"              {return new Symbol(LexicalUnit.ELSE, yyline, yycolumn, yytext());}
  "endif"             {return new Symbol(LexicalUnit.ENDIF, yyline, yycolumn, yytext());}
// Loop keywords
  "while"             {return new Symbol(LexicalUnit.WHILE, yyline, yycolumn, yytext());}
  "do"                {return new Symbol(LexicalUnit.DO, yyline, yycolumn, yytext());}
  "endwhile"          {return new Symbol(LexicalUnit.ENDWHILE, yyline, yycolumn, yytext());}
  "for"               {return new Symbol(LexicalUnit.FOR, yyline, yycolumn, yytext());}
  "from"              {return new Symbol(LexicalUnit.FROM, yyline, yycolumn, yytext());}
  "by"                {return new Symbol(LexicalUnit.BY, yyline, yycolumn, yytext());}
  "to"                {return new Symbol(LexicalUnit.TO, yyline, yycolumn, yytext());}
  "endfor"            {return new Symbol(LexicalUnit.ENDFOR, yyline, yycolumn, yytext());}
// Comparison operators
  "="                 {return new Symbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext());}
  ">"                 {return new Symbol(LexicalUnit.GREATER, yyline, yycolumn, yytext());}
  "<"                 {return new Symbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext());}
// IO keywords
  "print"             {return new Symbol(LexicalUnit.PRINT, yyline, yycolumn, yytext());}
  "read"              {return new Symbol(LexicalUnit.READ, yyline, yycolumn, yytext());}
// Numbers
  {BadInteger}        {System.err.println("Warning! Numbers with leading zeros are deprecated: " + yytext()); return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));}
  {Integer}           {return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));}
  {VarName}		      {return new Symbol(LexicalUnit.VARNAME,yyline, yycolumn,yytext());}
  {Separator}		  {}// ignore spaces
  [^]                 {throw new PatternSyntaxException("Unmatched token, out of symbols",yytext(),yyline);} // unmatched token gives an error
}