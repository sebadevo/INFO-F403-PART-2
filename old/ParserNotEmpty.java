package old;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.Lexer;
import src.Symbol;

public class Parser {
    private static Lexer lexer;
    private Symbol token;
    private ArrayList<Symbol> tokenList;

    public Parser(String file){
        try{
            lexer = new Lexer(new FileReader(file));
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    };

    public void getNextToken(){
        try {
            token = lexer.yylex();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void start(){
        getNextToken();
        switch (token.getValue().toString()){

        }
    }

    public void addToken(Symbol symbol){
        if (tokenList == null ){
            tokenList = new ArrayList<Symbol>();
            tokenList.add(symbol);
        }
        else{
            tokenList.add(symbol);            
        }
    }
}
