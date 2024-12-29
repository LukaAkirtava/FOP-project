import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ------------------------------------------------
        //  Additional small snippet to test basic arithmetic to check if it works:
        // ------------------------------------------------
        String snippet1 = ""
                + "x = 5\n"
                + "y = 10\n"
                + "z = x + y\n"
                + "print(z)\n"
                + "z = z * 2\n"
                + "print(z)\n";

        runSnippet(snippet1);
        System.out.println("-----");

        // ------------------------------------------------
        // 1. Sum of First N Numbers (N=10)
        // sum = 1+2+...+N
        // ------------------------------------------------
        String sumOfN = ""
                + "N = 10\n"
                + "sum = 0\n"
                + "i = 1\n"
                + "while i <= N: {\n"
                + "    sum = sum + i\n"
                + "    i = i + 1\n"
                + "}\n"
                + "print(sum)\n";

        runSnippet(sumOfN);
        System.out.println("-----");
    }
        private static void runSnippet (String sourceCode){
            System.out.println("Source Code:\n" + sourceCode);
            Lexer lexer = new Lexer(sourceCode);
            List<Token> tokens = lexer.tokenize();

            // (Optional) Print tokens for debugging:
            // for (Token t : tokens) {
            //     System.out.println(t);
            // }

            Interpreter interpreter = new Interpreter(tokens);
            interpreter.interpret();
        }
}




