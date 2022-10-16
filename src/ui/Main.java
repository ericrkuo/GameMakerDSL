package ui;

import ast.Program;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.DynamicCheck;
import parser.GameLexer;
import parser.GameParser;
import parser.ParseTreeToAST;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameLexer lexer = new GameLexer(CharStreams.fromFileName("src/complex_input.txt"));
        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Done tokenizing");

        GameParser parser = new GameParser(tokens);
        ParseTreeToAST visitor = new ParseTreeToAST();
        Program parsedProgram = visitor.visitProgram(parser.program());
        // dynamic check
        DynamicCheck dynamicCheck = new DynamicCheck(parsedProgram);
        dynamicCheck.check();
        System.out.println("Done parsing");

        // print out object for now
        App.createGame(parsedProgram);
    }
}
