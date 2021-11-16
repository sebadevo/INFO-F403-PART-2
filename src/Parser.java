package src;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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


    public void program(){
        if (token.getValue().toString() == "BEG"){
            code(); 
        }
    }

    public void code(){
        switch (token.getValue().toString()){
            case "END": 
            case "ENDIF": 
            case "ELSE":
            case "ENDWHILE":
            case "ENDFOR": 
            return; 
        }
        instList(); 
    }

    private void instList() {
        instruction(); 
        instTail(); 
    }


    private void instTail() {
        switch (token.getValue().toString()){
            case "END": 
            case "ENDIF": 
            case "ELSE":
            case "ENDWHILE":
            case "ENDFOR": 
            return; 
        }

        if (token.getValue().toString() == ";"){
            instList(); 
        }
    }


    private void instruction() {
        ifCondition(); 
        whileCondition(); 
        assign(); 
        forCondition();
        print(); 
        read(); 
    }


    private void read() {
    }


    private void print() {
    }


    private void forCondition() {
    }


    private void assign() {
    }


    private void whileCondition() {
    }


    private void ifCondition() {
        if (token.getValue().toString() == ";"){
            instList(); 
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

