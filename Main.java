import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        String s = "{if{startIf}else{StartElse}}"; // {b0if{b1startIf}else{b2StartElse}}

        // open file
        File file = new File("tt.txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("result.txt");
        FileWriter fileWriter = new FileWriter("result.txt");
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(fis);
        // create a lexer that feeds off of input CharStream
        Task1Lexer lexer = new Task1Lexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        Task1Parser parser = new Task1Parser(tokens);
        ParseTree tree = parser.block(); // begin parsing at init rule
        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks

        walker.walk(new MyTaskListener(), tree);
        fileWriter.write(MyTaskListener.result);
        fileWriter.close();
        System.out.println(); // print a \n after translation
    }
}
