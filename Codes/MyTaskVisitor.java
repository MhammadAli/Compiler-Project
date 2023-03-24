import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.HashMap;
import java.util.Map;

public class MyTaskVisitor  extends  JavaParserBaseVisitor<Void>{
    TokenStreamRewriter rewriter;

    public MyTaskVisitor() {}
    public MyTaskVisitor(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
    }

}
