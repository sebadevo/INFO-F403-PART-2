public class Parser {
    private static Lexer lexer;
    private Symbol token;

    public Parser(){
        lexer = new Lexer();
    };

    public void setLexer(){

    }

    public void getNextToken(){
        token = lexer.yylex();
    }
    

}
