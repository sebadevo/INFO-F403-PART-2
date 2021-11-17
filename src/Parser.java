package src;
import java.util.ArrayList;

public class Parser {
    private Symbol token;
    private String tokenString;
    private ArrayList<Symbol> tokenList;


    public void program(){
        if (tokenString == "BEG"){
            code(); 
        }
    }

    public void code(){
        switch (tokenString){
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
        switch (tokenString){
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
        if (tokenString == ";"){
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

    public void convertToken(Symbol symbol){
        tokenString = symbol.getValue().toString();
    }
}

