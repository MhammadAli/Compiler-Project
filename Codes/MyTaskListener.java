public class MyTaskListener extends Task1BaseListener {
    static int blockCounter = 0;
    static String result = "";

    @Override
    public void enterOpenCurlybraces(Task1Parser.OpenCurlybracesContext ctx) {
        super.enterOpenCurlybraces(ctx);
        System.out.print(ctx.getText()+"\n//Block "+blockCounter++ +"\n");
        result += ctx.getText()+"\n//Block "+blockCounter +"\n";
    }

    @Override
    public void enterStatementState(Task1Parser.StatementStateContext ctx) {
        super.enterStatementState(ctx);
        System.out.print(ctx.getText());
        result += ctx.getText();
    }

    @Override
    public void exitEndCurlybraces(Task1Parser.EndCurlybracesContext ctx) {
        super.exitEndCurlybraces(ctx);
        System.out.print(ctx.getText());
        result += ctx.getText();
    }
}
