import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private Symbol token;
    private Symbol matched;
    private LexicalUnit tokenUnit;
    private Lexer lexer;
    private ArrayList<Integer> leftMostD;

    public Parser(FileReader source){
        lexer = new Lexer(source);
    }

    public ParseTree beginParsing(){
        ParseTree parseTree = PROGRAM();
        System.out.println(leftMostD);
        return parseTree;
    }

    public ParseTree PROGRAM(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch (tokenUnit){
            case BEG:
                addLeftMostD(1);
                chdn.add(match(LexicalUnit.BEG));
                chdn.add(CODE()); 
                chdn.add(match(LexicalUnit.END));
                break;
            default:
                syntaxError(token); 
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("<Program>"), chdn);
        return parseTree;
    }

    private ParseTree CODE(){
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE: 
            case ENDWHILE: 
            case ENDFOR: addLeftMostD(3); return;  
            default:      
        }
        addLeftMostD(2);
        INSTLIST();
    }

    private void INSTLIST() {
        addLeftMostD(4);
        INSTRUCTION(); 
        INSTAIL();
    }

    private void INSTAIL() {
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE:
            case ENDWHILE:
            case ENDFOR: addLeftMostD(6); return; 
            case SEMICOLON:
                addLeftMostD(5);
                match(LexicalUnit.SEMICOLON); 
                INSTLIST();
                break;
            default:
                syntaxError(token);
                break;
        }

    }

    private void INSTRUCTION() {
        getNextToken();
        switch (tokenUnit){
            case IF: addLeftMostD(7); IF(); break;
            case WHILE: addLeftMostD(8); WHILE(); break;
            case VARNAME: addLeftMostD(9); ASSIGN(); break;
            case FOR: addLeftMostD(10); FOR(); break;
            case PRINT: addLeftMostD(11); PRINT(); break;
            case READ: addLeftMostD(12); READ(); break;
            default:
        }
    }

    private void IF(){
        getNextToken();
        switch(tokenUnit){
            case IF:
                addLeftMostD(13); 
                match(LexicalUnit.IF); 
                COND();
                match(LexicalUnit.THEN);
                CODE();
                TAIL();
                break;
            default:
                syntaxError(token);
                break;
        }
    }
    
    private void TAIL(){
        getNextToken();
        switch(tokenUnit){
            case ENDIF:
                addLeftMostD(14); 
                match(LexicalUnit.ENDIF);
                break;
            case ELSE:
                addLeftMostD(15);   
                match(LexicalUnit.ELSE);
                CODE();
                match(LexicalUnit.ENDIF);
                break;
            default:
                syntaxError(token);
                break;
        }
    }

    private void WHILE(){
        getNextToken();
        switch(tokenUnit){
            case WHILE:
                addLeftMostD(16); 
                match(LexicalUnit.WHILE); 
                COND();
                match(LexicalUnit.DO);
                CODE();
                match(LexicalUnit.ENDWHILE);
                break;
            default:
                syntaxError(token);
                break;
        }
    }
    
    private void COND(){
        getNextToken();
        switch(tokenUnit){
            case NOT:
                addLeftMostD(17); 
                match(LexicalUnit.NOT); 
                COND();
                break;
            default:
                addLeftMostD(18); 
                SIMPLECOND();
        }
        
    }

    private void SIMPLECOND(){
        addLeftMostD(19); 
        EXPRARITH();
        COMP();
        EXPRARITH();
    }

    private void COMP(){
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
                addLeftMostD(20); 
                match(LexicalUnit.EQUAL); 
                break;
            case GREATER:
                addLeftMostD(21);
                match(LexicalUnit.GREATER); 
                break;  
            case SMALLER:
                addLeftMostD(22);
                match(LexicalUnit.SMALLER); 
                break;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void EXPRARITH(){
        addLeftMostD(23);
        A();
        B();
    }

    private void A(){
        addLeftMostD(24);
        F();
    }

    private void B(){
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
            case GREATER:
            case SMALLER:
            case THEN:
            case DO:
            case LPAREN:
            case SEMICOLON:
            case END:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFOR:
            case BY:
            case TO: addLeftMostD(27); return;
            case PLUS:
                addLeftMostD(25);
                match(LexicalUnit.PLUS); 
                F();
                B();
                break;
            case MINUS:
                addLeftMostD(26);
                match(LexicalUnit.MINUS); 
                F();
                B();
                break;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void F(){
        addLeftMostD(28);
        C();
        D();
    }

    private void C(){
        addLeftMostD(29);
        G();
    }

    private void D(){
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
            case GREATER:
            case SMALLER:
            case THEN:
            case DO:
            case LPAREN:
            case SEMICOLON:
            case END:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFOR:
            case BY:
            case TO: 
            case PLUS:
            case MINUS: addLeftMostD(32);return;
            case TIMES:
                addLeftMostD(30);
                match(LexicalUnit.TIMES); 
                G();
                D();
                break;
            case DIVIDE:
                addLeftMostD(31);
                match(LexicalUnit.DIVIDE); 
                G();
                D();
                break;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void G(){
        getNextToken();
        switch(tokenUnit){
            case MINUS:
                addLeftMostD(33);
                match(LexicalUnit.MINUS); 
                G();
                break;
            case LPAREN:
                addLeftMostD(34);
                match(LexicalUnit.LPAREN); 
                EXPRARITH();
                match(LexicalUnit.RPAREN); 
                break;  
            default:
                addLeftMostD(35);
                H();
                break;
        }

    }

    private void H(){
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                addLeftMostD(36);
                match(LexicalUnit.VARNAME); 
                break;
            case NUMBER:
                addLeftMostD(37);
                match(LexicalUnit.NUMBER); 
                break;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void ASSIGN(){
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                addLeftMostD(38);
                match(LexicalUnit.VARNAME); 
                match(LexicalUnit.ASSIGN);
                EXPRARITH();
                break; 
            default:
                syntaxError(token);
                break;
        }
    }
    private void FOR(){
        getNextToken();
        switch(tokenUnit){
            case FOR:
                addLeftMostD(39);
                match(LexicalUnit.FOR); 
                match(LexicalUnit.VARNAME); 
                match(LexicalUnit.FROM); 
                EXPRARITH();
                match(LexicalUnit.BY);
                EXPRARITH();
                match(LexicalUnit.TO);
                EXPRARITH();
                match(LexicalUnit.DO);
                CODE();
                match(LexicalUnit.ENDFOR);
                break;
            default:
                syntaxError(token);
                break;
        }
    }
    private void PRINT(){
        addLeftMostD(40);
        match(LexicalUnit.PRINT);
        match(LexicalUnit.LPAREN); 
        match(LexicalUnit.VARNAME); 
        match(LexicalUnit.RPAREN); 
    }
    private void READ(){
        addLeftMostD(41);
        match(LexicalUnit.READ);
        match(LexicalUnit.LPAREN); 
        match(LexicalUnit.VARNAME); 
        match(LexicalUnit.RPAREN);
    }

    private void convertToken(){
        tokenUnit = token.getType();
    }

    private void addLeftMostD(int i) {
        if (leftMostD == null){
            leftMostD = new ArrayList<Integer>();
        }
        leftMostD.add(i);
    }

    private void getNextToken(){
        if (matched==null || matched.equals(token)){
            try{
                token = lexer.nextToken();
            } catch (IOException e){
                e.printStackTrace();
            }
            convertToken();
        }
    }

    private ParseTree match(LexicalUnit expected){
        if (matched!=null){getNextToken();}
        if (!expected.equals(tokenUnit)){
            syntaxError(token);
        }
        ParseTree root = new ParseTree(token);
        matched = token;
        return root;
    }

    // TODO rajouter le expected.
    private void syntaxError(Symbol symbol){
        System.err.println("An error occured when reading the token : " + symbol.getValue());
        System.exit(1);
    }


    private void print(String str){
        System.out.println(str);
    }
}