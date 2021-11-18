import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;
import javax.swing.plaf.synth.SynthButtonUI;

public class Parser {
    private Symbol token;
    private LexicalUnit tokenUnit;
    private Lexer lexer;
    private ArrayList<Symbol> tokenList;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public boolean program(){
        getNextToken();
        if (!tokenUnit.equals(LexicalUnit.BEG)) return syntax_error(token);
        code();
        getNextToken();
        if (!tokenUnit.equals(LexicalUnit.END)) return syntax_error(token);
    }

    public boolean code(){
        getNextToken();
        switch (tokenUnit){
            case END: return true;
            case ENDIF: 
            case ELSE: 
            case ENDWHILE: 
            case ENDFOR: getNextToken(); return true;
        }
        instList(); 
        
    }

    private void instList() {
        instruction(); 
        instTail(); 
    }


    private void instTail() {
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE:
            case ENDWHILE:
            case ENDFOR: 
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
        if (tokenUnit.equals(LexicalUnit.SEMICOLON)){
        instList(); 
        }
    }


    public void addToken(Symbol symbol){
        if (tokenList == null ){
            tokenList = new ArrayList<Symbol>();
            Symbol tok = symbol;
            tokenList.add(tok);
        }
        else{
            Symbol tok = symbol;
            tokenList.add(tok);          
        }
    }

    public void convertToken(){
        tokenUnit = token.getType();
    }

    public void getNextToken(){
        token = lexer.nextToken();
        convertToken();

    }

    public void match(String str){
        
    }

    public boolean syntax_error(Symbol symbol){
        System.out.println("error, we soundl't have met : " + symbol.getValue());
        return false;
    }
}

