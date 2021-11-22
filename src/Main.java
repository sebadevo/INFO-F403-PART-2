import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;



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
        // Display the usage when the number of arguments is wrong (should be 1 or 3)
        if(args.length < 1){
            System.out.println("Usage:  java -jar Part2.jar -wt output_file.tex input_file.co\n"
                             + "or\tjava "+Main.class.getSimpleName()+" file.co");
            System.exit(0);
        }else if (args.length > 3){
            System.err.println("Too many arguments passed in command line");
            System.exit(0);
        }

        String texFile=null;
        String inputFile=null;
        FileReader source=null;
        
        for (int i = 0; i<args.length;i++){
            if (args[i].equals("-wt")){
                if (i+1<args.length){
                    texFile = args[i+1];
                    i++;
                }else{
                    System.err.println("no file have been submited with the -wt command");
                    System.exit(0);
                }
            }
            else{
                if (inputFile==null){
                    inputFile = args[i];
                }
            }
        }

        if (inputFile==null){
            System.err.println("The input file has not been set in command line.");
            System.exit(0);
        }
        
        try{
            source = new FileReader(inputFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        final Parser parser = new Parser(source);
        ParseTree parseTree = parser.beginParsing();
        if (texFile!=null){
            try {
                FileWriter outPutFile = new FileWriter(texFile);
                outPutFile.write(parseTree.toLaTeX());
                outPutFile.close();
            }catch (IOException e){
                e.printStackTrace();    
            }
        }
        source.close();
    }
}
