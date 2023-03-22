public class MyTaskListener extends Task1BaseListener{
    static int blockCounter = 1;
    static String result = "";

    @Override
    public void enterOpenCurlybraces(Task1Parser.OpenCurlybracesContext ctx) {
        super.enterOpenCurlybraces(ctx);
        System.out.println("{// Block no."+blockCounter++);
        result += "{ Block no."+blockCounter+'\n';
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
    public void enterSentence(Task1Parser.SentenceContext ctx) {
        super.enterSentence(ctx);
        System.out.print(ctx.getText());
        result += ctx.getText();
    }
}
