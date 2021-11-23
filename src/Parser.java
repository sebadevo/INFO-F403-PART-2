import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;


public class Parser {
    private Symbol token;
    private Symbol matched;
    private LexicalUnit tokenUnit;
    private final Lexer lexer;
    private ArrayList<Integer> leftMostD;

    public Parser(FileReader source){
        lexer = new Lexer(source);
    }
    
    /**
     * We check if there is nothing after the last token. 
     * We print the list of the left most derivative 
     * @return parseTree
     */
    public ParseTree beginParsing(){
        ParseTree parseTree = PROGRAM();
        getNextToken();
        if (tokenUnit!=LexicalUnit.END_OF_STREAM){
            System.err.println("Sorry but "+token.toString() + " is after lexical unit: " + LexicalUnit.END);
            System.exit(0);
        }
        StringBuilder correctPrint = new StringBuilder();
        for (Integer integer : leftMostD) {
            correctPrint.append(integer.toString()).append(" ");
        }
        System.out.println(correctPrint);
        return parseTree;
    }

    /**
     * It is the corresponding function of the variable Program. 
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal).  
     */
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
        return new ParseTree(new Symbol("Program"), chdn);
    }

    /**
     * It is the corresponding function of the variable Code. 
     * Code -> InstList
     *      -> ε
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
                return new ParseTree(new Symbol("Code"), chdn);
            default:      
        }
        addLeftMostD(2);
        chdn.add(INSTLIST());
        return new ParseTree(new Symbol("Code"), chdn);
    }

    /**
     * It is the corresponding function of the variable InstList. 
     * InstList -> Instruction INSTTAIL 
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree INSTLIST() {
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(4);
        chdn.add(INSTRUCTION()); 
        chdn.add(INSTTAIL());
        return new ParseTree(new Symbol("InstList"), chdn);
    }

    /**
     * It is the corresponding function of the variable INSTTAIL. 
     * InstTail -> ; InstList
     *          -> ε
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree INSTTAIL() {
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
                return new ParseTree(new Symbol("INSTTAIL"), chdn);
            case SEMICOLON:
                addLeftMostD(5);
                chdn.add(match(LexicalUnit.SEMICOLON)); 
                chdn.add(INSTLIST());
                break;
            default:
                syntaxError(token);
                break;
        }
        return new ParseTree(new Symbol("INSTTAIL"), chdn);

    }

    /**
     * It is the corresponding function of the variable Instruction. 
     * Instruction -> If
     *             -> While
     *             -> Assign
     *             -> For
     *             -> Print
     *             -> Read
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("Instruction"), chdn);
    }

    /**
     * It is the corresponding function of the variable If. 
     * If -> if Cond then Code Tail
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("If"), chdn);
    }

    /**
     * It is the corresponding function of the variable Tail. 
     * Tail -> endif
     *      -> else Code endif
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("Tail"), chdn);
    }

    /**
     * It is the corresponding function of the variable While. 
     * While -> while Cond do Code endwhile
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("While"), chdn);
    }
    
    /**
     * It is the corresponding function of the variable Cond. 
     * Cond -> not Cond
     *      -> SimpleCond
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("Cond"), chdn);
        
    }


    /**
     * It is the corresponding function of the variable SimpleCond. 
     * SimpleCond -> ExprArith Comp ExprArith
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree SIMPLECOND(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(19); 
        chdn.add(EXPRARITH());
        chdn.add(COMP());
        chdn.add(EXPRARITH());
        return new ParseTree(new Symbol("SimpleCond"), chdn);
    }

    /**
     * It is the corresponding function of the variable Comp. 
     * Comp -> =
     *      -> >
     *      -> <
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("Comp"), chdn);
    }


    /**
     * It is the corresponding function of the variable ExprArith. 
     * ExprArith -> A B
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree EXPRARITH(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(23);
        chdn.add(A());
        chdn.add(B());
        return new ParseTree(new Symbol("ExprArith"), chdn);
    }

    /**
     * It is the corresponding function of the variable A. 
     * A -> F
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree A(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(24);
        chdn.add(F());
        return new ParseTree(new Symbol("A"), chdn);
    }

     /**
     * It is the corresponding function of the variable B. 
     * B -> + F B
     *   -> - F B 
     *   -> ε
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree B(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
            case GREATER:
            case SMALLER:
            case THEN:
            case DO:
            case RPAREN:
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
                return new ParseTree(new Symbol("B"), chdn);
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
        return new ParseTree(new Symbol("B"), chdn);
    }

    /**
     * It is the corresponding function of the variable F. 
     * F -> C D
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree F(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(28);
        chdn.add(C());
        chdn.add(D());
        return new ParseTree(new Symbol("F"), chdn);
    }

    /**
     * It is the corresponding function of the variable C. 
     * C -> G
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree C(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(29);
        chdn.add(G());
        return new ParseTree(new Symbol("C"), chdn);
    }

    /**
     * It is the corresponding function of the variable D. 
     * D -> * G D
     *   -> / G D 
     *   -> ε
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree D(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        getNextToken();
        switch(tokenUnit){
            case EQUAL:
            case GREATER:
            case SMALLER:
            case THEN:
            case DO:
            case RPAREN:
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
                return new ParseTree(new Symbol("D"), chdn);
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
                System.out.println("bonjour");
                syntaxError(token);
                break;
        }
        return new ParseTree(new Symbol("D"), chdn);
    }

    /**
     * It is the corresponding function of the variable G. 
     * G -> - G
     *   -> ExprArith
     *   -> H
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("G"), chdn);
    }

    /**
     * It is the corresponding function of the variable H. 
     * H -> [VarName]
     *   -> [Number]
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("H"), chdn);
    }

    /**
     * It is the corresponding function of the variable Assign. 
     * Assign -> [VarName] := ExprArith
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("Assign"), chdn);
    }

    /**
     * It is the corresponding function of the variable For. 
     * For -> for [VarName] from ExprArith by ExprArith to ExprArith do Code end for 
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
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
        return new ParseTree(new Symbol("For"), chdn);
    }

    /**
     * It is the corresponding function of the variable Print. 
     * Print -> print( [VarName] )
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree PRINT(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(40);
        chdn.add(match(LexicalUnit.PRINT));
        chdn.add(match(LexicalUnit.LPAREN)); 
        chdn.add(match(LexicalUnit.VARNAME)); 
        chdn.add(match(LexicalUnit.RPAREN));
        return new ParseTree(new Symbol("Print"), chdn);
    }

    /**
     * It is the corresponding function of the variable Read. 
     * Read -> read( [VarName] )
     * @return parseTree where the root is Program (variable) and the leaf is end (terminal). 
     */
    private ParseTree READ(){
        ArrayList<ParseTree> chdn = new ArrayList<>();
        addLeftMostD(41);
        chdn.add(match(LexicalUnit.READ));
        chdn.add(match(LexicalUnit.LPAREN)); 
        chdn.add(match(LexicalUnit.VARNAME)); 
        chdn.add(match(LexicalUnit.RPAREN));
        return new ParseTree(new Symbol("Read"), chdn);
    }

    /**
     * Stores the type of the token in tokenUnit
     */
    private void convertToken(){
        tokenUnit = token.getType();
    }

    /**
     * Adds the rule number i to the list leftMostD. 
     * @param i the last rule that has been used.
     */
    private void addLeftMostD(int i) {
        if (leftMostD == null){
            leftMostD = new ArrayList<>();
        }
        leftMostD.add(i);
    }

    /**
     * Fetches the following token if matched is null or equals to the current token. 
     */
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

    /***
     * Launches an error if the token is not what was expected. 
     * Adds the token to the root of the parseTree. 
     * @param expected The expected lexicalUnit
     * @return root
     */
    private ParseTree match(LexicalUnit expected){
        if (matched!=null){getNextToken();}
        if (!expected.equals(tokenUnit)){
            syntaxError(token);
        }
        ParseTree root = new ParseTree(token);
        matched = token;
        return root;
    }

    /**
     * Launches an error and interrupts the code. 
     * @param symbol the symbol that generated the error.
     */
    private void syntaxError(Symbol symbol){
        System.err.println("An error occured when reading the token : " + symbol.getValue()+" at ligne : " + symbol.getLine());
        System.exit(1);
    }
}