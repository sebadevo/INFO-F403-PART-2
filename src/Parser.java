import java.io.IOException;

public class Parser {
    private Symbol token;
    private Symbol matched;
    private LexicalUnit tokenUnit;
    private Lexer lexer;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public void PROGRAM(){
        getNextToken();
        switch (tokenUnit){
            case BEG:
                match(LexicalUnit.BEG);
                CODE(); 
                match(LexicalUnit.END);
                return;
            default:
                syntaxError(token); 
                break;
        }

    }

    private void CODE(){
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE: 
            case ENDWHILE: 
            case ENDFOR: return;  
            default:      
        }
        INSTLIST(); 
    }

    private void INSTLIST() {
        INSTRUCTION(); 
        INSTAIL();
    }

    private void INSTRUCTION() {
        getNextToken();
        switch (tokenUnit){
            case IF: IF(); return;
            case WHILE: WHILE(); return;
            case VARNAME: ASSIGN(); return;
            case FOR: FOR(); return;
            case PRINT: PRINT(); return;
            case READ: READ(); return;
            default:
        }
    }


    private void INSTAIL() {
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE:
            case ENDWHILE:
            case ENDFOR: return; 
            case SEMICOLON:
                match(LexicalUnit.SEMICOLON); INSTLIST();
                return;
            default:
                syntaxError(token);
                break;
        }

    }

    private void IF(){
        getNextToken();
        switch(tokenUnit){
            case IF:
                match(LexicalUnit.IF); 
                COND();
                match(LexicalUnit.THEN);
                CODE();
                TAIL();
                return;
            default:
                syntaxError(token);
                break;
        }
    }
    
    private void TAIL(){
        getNextToken();
        switch(tokenUnit){
            case ENDIF:
                match(LexicalUnit.ENDIF);
                return;
            case ELSE:
                match(LexicalUnit.ELSE);
                CODE();
                match(LexicalUnit.ENDIF);
                return;
            default:
                syntaxError(token);
                break;
        }
    }

    private void WHILE(){
        getNextToken();
        switch(tokenUnit){
            case WHILE:
                match(LexicalUnit.WHILE); 
                COND();
                match(LexicalUnit.DO);
                CODE();
                match(LexicalUnit.ENDWHILE);
                return;
            default:
                syntaxError(token);
                break;
        }
    }
    
    private void COND(){
        getNextToken();
        switch(tokenUnit){
            case NOT:
                match(LexicalUnit.NOT); 
                COND();
                return;
            default:
        }
        SIMPLECOND();
    }

    private void SIMPLECOND(){
        EXPRARITH();
        COMP();
        EXPRARITH();
    }

    private void COMP(){
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
                match(LexicalUnit.EQUAL); 
                return;
            case GREATER:
                match(LexicalUnit.GREATER); 
                return;  
            case SMALLER:
                match(LexicalUnit.SMALLER); 
                return;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void EXPRARITH(){
        A();
        B();
    }

    private void A(){
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
            case TO: return;
            case PLUS:
                match(LexicalUnit.PLUS); 
                F();
                B();
                return;
            case MINUS:
                match(LexicalUnit.MINUS); 
                F();
                B();
                return;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void F(){
        C();
        D();
    }

    private void C(){
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
            case MINUS: return;
            case TIMES:
                match(LexicalUnit.TIMES); 
                G();
                D();
                return;
            case DIVIDE:
                match(LexicalUnit.DIVIDE); 
                G();
                D();
                return;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void G(){
        getNextToken();
        switch(tokenUnit){
            case MINUS:
                match(LexicalUnit.MINUS); 
                G();
                return;
            case LPAREN:
                match(LexicalUnit.LPAREN); 
                EXPRARITH();
                match(LexicalUnit.RPAREN); 
                return;  
            default:
        }
        H();
    }

    private void H(){
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                match(LexicalUnit.VARNAME); 
                return;
            case NUMBER:
                match(LexicalUnit.NUMBER); 
                return;  
            default:
                syntaxError(token);
                break;
        }
    }

    private void ASSIGN(){
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                match(LexicalUnit.VARNAME); 
                match(LexicalUnit.ASSIGN);
                EXPRARITH();
                return; 
            default:
                syntaxError(token);
                break;
        }
    }
    private void FOR(){
        getNextToken();
        switch(tokenUnit){
            case FOR:
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
                return;
            default:
                syntaxError(token);
                break;
        }
    }
    private void PRINT(){
        match(LexicalUnit.PRINT);
        match(LexicalUnit.LPAREN); 
        match(LexicalUnit.VARNAME); 
        match(LexicalUnit.RPAREN); 
    }
    private void READ(){
        match(LexicalUnit.READ);
        match(LexicalUnit.LPAREN); 
        match(LexicalUnit.VARNAME); 
        match(LexicalUnit.RPAREN);
    }

    private void convertToken(){
        tokenUnit = token.getType();
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

    private void match(LexicalUnit expected){
        if (matched!=null){getNextToken();}
        System.out.println(token.getValue());
        if (!expected.equals(tokenUnit)){
            print("expected :" + expected + "got : " + tokenUnit);
            syntaxError(token);
        }
        matched = token;
    }

    private void syntaxError(Symbol symbol){
        System.err.println("An error occured when reading the token : " + symbol.getValue());
        System.exit(1);
    }

    private void print(String str){
        System.out.println(str);
    }
}

