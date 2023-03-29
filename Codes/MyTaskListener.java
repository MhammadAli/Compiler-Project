import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileWriter;

public class MyTaskListener extends JavaParserBaseListener {
    int counter;
    TokenStreamRewriter rewriter;

    public MyTaskListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.counter = 0;
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        String imports = """
                import java.io.*;
                import java.util.*;
                """;
        rewriter.insertBefore(ctx.start,imports);
        super.enterCompilationUnit(ctx);
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        this.counter++;
        rewriter.insertAfter(ctx.getStart(), "//block number " + this.counter + "\n");//write after {
        if (counter == 1) {//in main
            rewriter.insertBefore(ctx.getStart(),"throws Exception ");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "File output = new File(\"output.txt\");" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "output.createNewFile();" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "FileWriter w = new FileWriter(\"output.txt\");" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "w.write(\"block " + this.counter + " is Visited \" +\"\\n\");" + "\n");

            rewriter.insertBefore(ctx.getStop(), "w.close();" + "\n");//the last }
        } else {
            rewriter.insertBefore(ctx.getStop(), "w.write(\"block " + this.counter + " is Visited\" +\"\\n\");" + "\n");
        }

    }

    @Override
    public void exitIfExpression(JavaParser.IfExpressionContext ctx) {
        super.exitIfExpression(ctx);
        counter++;
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        super.exitStatement(ctx);
        if (ctx.elso != null)
            counter++;
    }
}
