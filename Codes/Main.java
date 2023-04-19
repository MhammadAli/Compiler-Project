import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <=5 ; i++){
            String inputFile = "inputs/input"+i+".java";
            FileInputStream inputStream = new FileInputStream(inputFile);
            ANTLRInputStream input = new ANTLRInputStream(inputStream);
            JavaLexer lexer = new JavaLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);
            ParseTree tree = parser.compilationUnit();
            ParseTreeWalker walker = new ParseTreeWalker();
            TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
            walker.walk(new MyTaskListener(rewriter,i), tree);

            File output = new File("src/outputs/output"+i+".java");
            output.createNewFile();
            FileWriter w = new FileWriter("src/outputs/output"+i+".java");
            w.write(rewriter.getText());
            w.close();
        }
        HTMLGen.main();

    }
}
