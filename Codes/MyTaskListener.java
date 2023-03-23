import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyTaskListener extends JavaParserBaseListener{
    static int blockCounter = 1;
    static String result = "";
    TokenStreamRewriter rewriter;

    public MyTaskListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        rewriter.insertAfter(ctx.getStart(), "//Block no. " + blockCounter++ + "\n");
    }
    



/*
@Override
public void enterOpenCurlybraces(Task1Parser.OpenCurlybracesContext ctx) {
    super.enterOpenCurlybraces(ctx);
    System.out.println("{// Block no."+blockCounter);
    result += "{ Block no."+blockCounter+'\n';
    blockCounter++;
}

@Override
    public void enterStatement_state(Task1Parser.Statement_stateContext ctx) {
        super.enterStatement_state(ctx);
        System.out.print(ctx.getText());
        result += ctx.getText();
    }
    
    @Override
    public void enterEndCurlybraces(Task1Parser.EndCurlybracesContext ctx) {
        super.enterEndCurlybraces(ctx);
        System.out.println(ctx.getText());
        result += ctx.getText();
    }
    
    @Override
    public void enterExperssion(Task1Parser.ExperssionContext ctx) {
        super.enterExperssion(ctx);
        System.out.println(ctx.getText());
        result += ctx.getText();
    }
    */
}
