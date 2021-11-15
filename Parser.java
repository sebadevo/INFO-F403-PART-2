import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private static Lexer lexer;
    private Symbol token;

    public Parser(String file){
        try{
            lexer = new Lexer(new FileReader(file));
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    };

    public void start(){
        
    }

    public void getNextToken(){
        try {
            token = lexer.yylex();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    

}
