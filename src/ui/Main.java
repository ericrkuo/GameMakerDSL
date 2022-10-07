package ui;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.EndlessRunnerMakerLexer;

import org.antlr.v4.runtime.CharStreams;
import parser.EndlessRunnerMakerParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EndlessRunnerMakerLexer lexer = new EndlessRunnerMakerLexer(CharStreams.fromFileName("src/input.txt"));
        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Done tokenizing");

        EndlessRunnerMakerParser parser = new EndlessRunnerMakerParser(tokens);
        // TODO ParseToAST and remaining logic
    }
}
