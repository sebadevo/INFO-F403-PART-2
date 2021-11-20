import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.TreeMap;


import java.util.Map;

/**
 *
 * Project Part 1: Lexical Analyzer
 *
 * @author Marie Van Den Bogaard, Léo Exibard, Gilles Geeraerts
 *
 */

public class Main{
    /**
     *
     * The scanner
     *
     * @param args  The argument(s) given to the program
     * @throws IOException java.io.IOException if an I/O-Error occurs
     * @throws FileNotFoundException java.io.FileNotFoundException if the specified file does not exist
     *
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SecurityException{
        // Display the usage when the number of arguments is wrong (should be 1)
        if(args.length != 1){
            System.out.println("Usage:  java -jar Part1.jar file.fs\n"
                             + "or\tjava "+Main.class.getSimpleName()+" file.fs");
            System.exit(0);
        }

        // Open the file given in argument
        FileReader source = new FileReader(args[0]);

        /**
         * 
         */
        final Parser parser = new Parser(source);
        //parser.beginParsing();
        parser.PROGRAM();
        /**
         * The variables, sorted in a TreeMap, which maps the name of the variable to its corresponding Symbol.
         */
        TreeMap<String,Symbol> variablesTable = new TreeMap<String,Symbol>();
        // symbol represents the currently read symbol
    }
}
