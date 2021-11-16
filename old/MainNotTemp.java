package old;
import old.Parser;

public class MainNotTemp {
    public static void main(String[] args){
        Lexer.main(argv);
        Parser parser = new Parser(args[2]);
        parser.start();
    }
}
