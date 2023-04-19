import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyTaskListener extends JavaParserBaseListener {
    int counter;
    int fileNumber;
    boolean isMain = false;
    int arrayCounter;
    TokenStreamRewriter rewriter;

    public MyTaskListener(TokenStreamRewriter rewriter,int fileNumber) {
        this.rewriter = rewriter;
        this.counter = 0;
        this.fileNumber = fileNumber;
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        String imports = """
                package outputs;
                import java.io.*;
                import java.util.*;
                """;
        rewriter.insertBefore(ctx.start, imports);
        super.enterCompilationUnit(ctx);
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        super.enterClassBody(ctx);
        rewriter.insertAfter(ctx.start, "\nstatic int [] arr = new int[8];");
    }


    @Override
    public void enterIfStatement(JavaParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");


    }


    @Override
    public void enterElseStatement(JavaParser.ElseStatementContext ctx) {
        super.enterElseStatement(ctx);
        rewriter.insertAfter(ctx.start, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");

    }

    @Override
    public void enterElseIfStatement(JavaParser.ElseIfStatementContext ctx) {
        super.enterElseIfStatement(ctx);
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
    }

    @Override
    public void enterForStatement(JavaParser.ForStatementContext ctx) {
        super.enterForStatement(ctx);
        counter++;
        rewriter.insertBefore(ctx.statement().start, "{");
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
    }


    @Override
    public void enterWhileStatement(JavaParser.WhileStatementContext ctx) {
        super.enterWhileStatement(ctx);
        counter++;
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
    }


    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        super.enterMethodDeclaration(ctx);
        counter++;
        if (ctx.identifier().IDENTIFIER().getText().equals("main")) {
            isMain = true;

        }
    }

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
        super.enterMethodBody(ctx);
        if (isMain) {
            rewriter.insertBefore(ctx.getStart(), "throws Exception ");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "File output = new File(\"output"+fileNumber+".txt\");" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "output.createNewFile();" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "FileWriter w = new FileWriter(\"output"+fileNumber+".txt\");" + "\n");

            rewriter.insertBefore(ctx.getStop(), "w.close();" + "\n");//the last
            rewriter.insertBefore(ctx.getStop(), "w.write(Arrays.toString(arr));" + "\n");
            isMain = false;
        } else {
            rewriter.insertAfter(ctx.start, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        }
    }
}
