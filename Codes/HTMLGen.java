import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class HTMLGen {
    public static void main(String[] args) throws Exception {

           String inputFile = "src/outputs/output5.java";
           FileInputStream inputStream = new FileInputStream(inputFile);
           ANTLRInputStream input = new ANTLRInputStream(inputStream);
           JavaLexer lexer = new JavaLexer(input);
           CommonTokenStream tokens = new CommonTokenStream(lexer);
           JavaParser parser = new JavaParser(tokens);
           ParseTree tree = parser.compilationUnit();
           ParseTreeWalker walker = new ParseTreeWalker();
           TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
           walker.walk(new HTMLListener(rewriter,"output.txt"), tree);

           File output = new File("html/output.html");
           output.createNewFile();
           FileWriter w = new FileWriter("html/output.html");
           w.write(rewriter.getText());
           w.close();

    }
}
