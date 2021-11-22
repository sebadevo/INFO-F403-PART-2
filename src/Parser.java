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
        getNextToken();
        if (tokenUnit!=LexicalUnit.END_OF_STREAM){
            System.err.println("Sorry but "+token.toString() + " is after lexical unit: " + LexicalUnit.END);
            System.exit(0);
        }
        String correctPrint = "";
        for (int i = 0; i<leftMostD.size(); i++){
            correctPrint += leftMostD.get(i).toString()+ " ";
        }
        System.out.println(correctPrint);
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
        ParseTree parseTree = new ParseTree(new Symbol("Program"), chdn);
        return parseTree;
    }

    private ParseTree CODE(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE: 
            case ENDWHILE: 
            case ENDFOR: 
                addLeftMostD(3);
                chdn.add(new ParseTree(new Symbol("$\\varepsilon$" )));
                ParseTree parseTree = new ParseTree(new Symbol("Code"), chdn);
                return parseTree;
            default:      
        }
        addLeftMostD(2);
        chdn.add(INSTLIST());
        ParseTree parseTree = new ParseTree(new Symbol("Code"), chdn);
        return parseTree;
    }

    private ParseTree INSTLIST() {
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(4);
        chdn.add(INSTRUCTION()); 
        chdn.add(INSTAIL());
        ParseTree parseTree = new ParseTree(new Symbol("InstList"), chdn);
        return parseTree;
    }

    private ParseTree INSTAIL() {
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch (tokenUnit){
            case END: 
            case ENDIF: 
            case ELSE:
            case ENDWHILE:
            case ENDFOR: 
                addLeftMostD(6);
                chdn.add(new ParseTree(new Symbol("$\\varepsilon$")));
                ParseTree parseTree = new ParseTree(new Symbol("InsTail"), chdn);
                return parseTree;
            case SEMICOLON:
                addLeftMostD(5);
                chdn.add(match(LexicalUnit.SEMICOLON)); 
                chdn.add(INSTLIST());
                break;
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("InsTail"), chdn);
        return parseTree;

    }

    private ParseTree INSTRUCTION() {
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch (tokenUnit){
            case IF: addLeftMostD(7); chdn.add(IF()); break;
            case WHILE: addLeftMostD(8); chdn.add(WHILE()); break;
            case VARNAME: addLeftMostD(9); chdn.add(ASSIGN()); break;
            case FOR: addLeftMostD(10); chdn.add(FOR()); break;
            case PRINT: addLeftMostD(11); chdn.add(PRINT()); break;
            case READ: addLeftMostD(12); chdn.add(READ()); break;
            default:
        }
        ParseTree parseTree = new ParseTree(new Symbol("Instruction"), chdn);
        return parseTree;
    }

    private ParseTree IF(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case IF:
                addLeftMostD(13); 
                chdn.add(match(LexicalUnit.IF)); 
                chdn.add(COND());
                chdn.add(match(LexicalUnit.THEN));
                chdn.add(CODE());
                chdn.add(TAIL());
                break;
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("If"), chdn);
        return parseTree;
    }
    
    private ParseTree TAIL(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case ENDIF:
                addLeftMostD(14); 
                chdn.add(match(LexicalUnit.ENDIF));
                break;
            case ELSE:
                addLeftMostD(15);   
                chdn.add(match(LexicalUnit.ELSE));
                chdn.add(CODE());
                chdn.add(match(LexicalUnit.ENDIF));
                break;
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("Tail"), chdn);
        return parseTree;
    }

    private ParseTree WHILE(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case WHILE:
                addLeftMostD(16); 
                chdn.add(match(LexicalUnit.WHILE)); 
                chdn.add(COND());
                chdn.add(match(LexicalUnit.DO));
                chdn.add(CODE());
                chdn.add(match(LexicalUnit.ENDWHILE));
                break;
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("While"), chdn);
        return parseTree;
    }
    
    private ParseTree COND(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case NOT:
                addLeftMostD(17); 
                chdn.add(match(LexicalUnit.NOT)); 
                chdn.add(COND());
                break;
            default:
                addLeftMostD(18); 
                chdn.add(SIMPLECOND());
        }
        ParseTree parseTree = new ParseTree(new Symbol("Cond"), chdn);
        return parseTree;
        
    }

    private ParseTree SIMPLECOND(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(19); 
        chdn.add(EXPRARITH());
        chdn.add(COMP());
        chdn.add(EXPRARITH());
        ParseTree parseTree = new ParseTree(new Symbol("SimpleCond"), chdn);
        return parseTree;
    }

    private ParseTree COMP(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
                addLeftMostD(20); 
                chdn.add(match(LexicalUnit.EQUAL)); 
                break;
            case GREATER:
                addLeftMostD(21);
                chdn.add(match(LexicalUnit.GREATER)); 
                break;  
            case SMALLER:
                addLeftMostD(22);
                chdn.add(match(LexicalUnit.SMALLER)); 
                break;  
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("Comp"), chdn);
        return parseTree;
    }

    private ParseTree EXPRARITH(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(23);
        chdn.add(A());
        chdn.add(B());
        ParseTree parseTree = new ParseTree(new Symbol("ExprArith"), chdn);
        return parseTree;
    }

    private ParseTree A(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(24);
        chdn.add(F());
        ParseTree parseTree = new ParseTree(new Symbol("A"), chdn);
        return parseTree;
    }

    private ParseTree B(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
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
                addLeftMostD(27);
                chdn.add(new ParseTree(new Symbol("$\\varepsilon$")));
                ParseTree parseTree = new ParseTree(new Symbol("B"), chdn);
                return parseTree;
            case PLUS:
                addLeftMostD(25);
                chdn.add(match(LexicalUnit.PLUS)); 
                chdn.add(F());
                chdn.add(B());
                break;
            case MINUS:
                addLeftMostD(26);
                chdn.add(match(LexicalUnit.MINUS)); 
                chdn.add(F());
                chdn.add(B());
                break;  
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("B"), chdn);
        return parseTree;
    }

    private ParseTree F(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(28);
        chdn.add(C());
        chdn.add(D());
        ParseTree parseTree = new ParseTree(new Symbol("F"), chdn);
        return parseTree;
    }

    private ParseTree C(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(29);
        chdn.add(G());
        ParseTree parseTree = new ParseTree(new Symbol("C"), chdn);
        return parseTree;
    }

    private ParseTree D(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
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
            case MINUS:
                addLeftMostD(32);
                chdn.add(new ParseTree(new Symbol("$\\varepsilon$")));
                ParseTree parseTree = new ParseTree(new Symbol("D"), chdn);
                return parseTree;
            case TIMES:
                addLeftMostD(30);
                chdn.add(match(LexicalUnit.TIMES)); 
                chdn.add(G());
                chdn.add(D());
                break;
            case DIVIDE:
                addLeftMostD(31);
                chdn.add(match(LexicalUnit.DIVIDE)); 
                chdn.add(G());
                chdn.add(D());
                break;  
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("D"), chdn);
        return parseTree;
    }

    private ParseTree G(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case MINUS:
                addLeftMostD(33);
                chdn.add(match(LexicalUnit.MINUS)); 
                chdn.add(G());
                break;
            case LPAREN:
                addLeftMostD(34);
                chdn.add(match(LexicalUnit.LPAREN)); 
                chdn.add(EXPRARITH());
                chdn.add(match(LexicalUnit.RPAREN)); 
                break;  
            default:
                addLeftMostD(35);
                chdn.add(H());
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("G"), chdn);
        return parseTree;
    }

    private ParseTree H(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                addLeftMostD(36);
                chdn.add(match(LexicalUnit.VARNAME)); 
                break;
            case NUMBER:
                addLeftMostD(37);
                chdn.add(match(LexicalUnit.NUMBER)); 
                break;  
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("H"), chdn);
        return parseTree;
    }

    private ParseTree ASSIGN(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case VARNAME:
                addLeftMostD(38);
                chdn.add(match(LexicalUnit.VARNAME)); 
                match(LexicalUnit.ASSIGN);
                chdn.add(EXPRARITH());
                break; 
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("Assign"), chdn);
        return parseTree;
    }
    private ParseTree FOR(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case FOR:
                addLeftMostD(39);
                chdn.add(match(LexicalUnit.FOR)); 
                chdn.add(match(LexicalUnit.VARNAME)); 
                chdn.add(match(LexicalUnit.FROM)); 
                chdn.add(EXPRARITH());
                chdn.add(match(LexicalUnit.BY));
                chdn.add(EXPRARITH());
                chdn.add(match(LexicalUnit.TO));
                chdn.add(EXPRARITH());
                chdn.add(match(LexicalUnit.DO));
                chdn.add(CODE());
                chdn.add(match(LexicalUnit.ENDFOR));
                break;
            default:
                syntaxError(token);
                break;
        }
        ParseTree parseTree = new ParseTree(new Symbol("For"), chdn);
        return parseTree;
    }
    private ParseTree PRINT(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(40);
        chdn.add(match(LexicalUnit.PRINT));
        chdn.add(match(LexicalUnit.LPAREN)); 
        chdn.add(match(LexicalUnit.VARNAME)); 
        chdn.add(match(LexicalUnit.RPAREN)); 
        ParseTree parseTree = new ParseTree(new Symbol("Print"), chdn);
        return parseTree;
    }
    private ParseTree READ(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(41);
        chdn.add(match(LexicalUnit.READ));
        chdn.add(match(LexicalUnit.LPAREN)); 
        chdn.add(match(LexicalUnit.VARNAME)); 
        chdn.add(match(LexicalUnit.RPAREN));
        ParseTree parseTree = new ParseTree(new Symbol("Read"), chdn);
        return parseTree;
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

    // TOD rajouter le expected.
    private void syntaxError(Symbol symbol){
        System.err.println("An error occured when reading the token : " + symbol.getValue());
        System.exit(1);
    }
}