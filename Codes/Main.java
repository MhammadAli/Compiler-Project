import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        String s = "{if{startIf}else{StartElse}}"; // {b0if{b1startIf}else{b2StartElse}}

        // open file
        File file = new File("test_text.txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("result.txt");
        FileWriter fileWriter = new FileWriter("result.txt");
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(fis);
        // create a lexer that feeds off of input CharStream
        JavaLexer lexer = new JavaLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        // Walk the tree created during the parse, trigger callbacks
        
        walker.walk(new MyTaskListener(rewriter), tree);
        fileWriter.write(rewriter.getText);
        fileWriter.close();
        System.out.println(); // print a \n after translation
    }
}
