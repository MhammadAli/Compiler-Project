import org.antlr.v4.runtime.TokenStreamRewriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HTMLListener extends JavaParserBaseListener {
    int counter;
    String file;
    boolean isMain = false;
    int arrayCounter;
    String[] divNumbers;
    TokenStreamRewriter rewriter;

    public HTMLListener(TokenStreamRewriter rewriter,String file) throws FileNotFoundException {
        this.rewriter = rewriter;
        this.counter = 0;
        this.file = file;
       String fileContent =  readFileToString(file);
        divNumbers = fileContent.substring(1, fileContent.length() - 1).split(", ");

    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        super.enterCompilationUnit(ctx);
        rewriter.insertBefore(ctx.start, "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"LimeGreen\">");
    }

    @Override
    public void exitClassBody(JavaParser.ClassBodyContext ctx) {
        super.exitClassBody(ctx);
        rewriter.insertAfter(ctx.stop, "</pre>");
        rewriter.insertAfter(ctx.stop, "\n</body>\n" +
                "</html>");
    }

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        super.enterImportDeclaration(ctx);
        rewriter.insertBefore(ctx.start, "<pre>");
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        super.enterClassBody(ctx);
        rewriter.insertAfter(ctx.start, "\nstatic int [] arr = new int[8];");
    }


    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        super.enterClassDeclaration(ctx);
        rewriter.insertBefore(ctx.start, "<pre>");
    }

    @Override
    public void enterIfStatement(JavaParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
//        rewriter.insertBefore(ctx.start,"<pre>");
//        System.out.println(ctx.parExpression().expression().getText());
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
        rewriter.insertAfter(ctx.statement().stop, "</pre>");


    }


    @Override
    public void enterElseStatement(JavaParser.ElseStatementContext ctx) {
        super.enterElseStatement(ctx);
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }
        rewriter.insertAfter(ctx.start, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
        rewriter.insertAfter(ctx.statement().stop, "</pre>");

    }

    @Override
    public void enterElseIfStatement(JavaParser.ElseIfStatementContext ctx) {
        super.enterElseIfStatement(ctx);
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        counter++;
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
        rewriter.insertAfter(ctx.statement().stop, "</pre>");
    }

    @Override
    public void enterForStatement(JavaParser.ForStatementContext ctx) {
        super.enterForStatement(ctx);
        System.out.println((divNumbers[counter]));
        System.out.println(counter);
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }
        counter++;
        rewriter.insertBefore(ctx.statement().start, "{");
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
        rewriter.insertAfter(ctx.statement().stop, "</pre>");
    }


    @Override
    public void enterWhileStatement(JavaParser.WhileStatementContext ctx) {
        super.enterWhileStatement(ctx);
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }
        counter++;
        rewriter.insertAfter(ctx.parExpression().stop, "{");
        rewriter.insertAfter(ctx.statement().stop, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        rewriter.insertAfter(ctx.statement().stop, "\n}");
        rewriter.insertAfter(ctx.statement().stop, "</pre>");
    }


    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        super.enterMethodDeclaration(ctx);
        counter++;
        if (divNumbers[counter-1].equals("0")) {
            rewriter.insertBefore(ctx.start, "<pre style=\"background-color: red;\">");
        } else {
            rewriter.insertBefore(ctx.start, "<pre>");

        }

        if (ctx.identifier().IDENTIFIER().getText().equals("main")) {
            isMain = true;

        }

    }

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
        super.enterMethodBody(ctx);
        if (isMain) {
            rewriter.insertBefore(ctx.getStart(), "throws Exception ");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "File output = new File(\"output.txt\");" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "output.createNewFile();" + "\n");
            rewriter.insertAfter(ctx.getStart(), "\t" + "\t" + "FileWriter w = new FileWriter(\"output.txt\");" + "\n");

            rewriter.insertBefore(ctx.getStop(), "w.close();" + "\n");//the last
            rewriter.insertBefore(ctx.getStop(), "w.write(Arrays.toString(arr));" + "\n");
            isMain = false;
        } else {
            rewriter.insertAfter(ctx.start, "\narr[" + arrayCounter++ + "] = " + counter + ";");
        }

        rewriter.insertAfter(ctx.stop, "</pre>");
    }


    public static String readFileToString(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();
    }


}
