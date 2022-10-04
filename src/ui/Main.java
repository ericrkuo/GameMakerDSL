package ui;

import ast.*;
import com.google.gson.*;
import org.antlr.v4.runtime.*;
import parser.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameLexer lexer = new GameLexer(CharStreams.fromFileName("src/input.txt"));
        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Done tokenizing");

        GameParser parser = new GameParser(tokens);
        ParseTreeToAST visitor = new ParseTreeToAST();
        Program parsedProgram = visitor.visitProgram(parser.program());
        System.out.println("Done parsing");

        // print out object for now
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonInString = gson.toJson(parsedProgram);
        System.out.println(jsonInString);
    }
}
