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

        // ------------------------------------------------
        // 2. Factorial of N (N=5 => 120)
        // ------------------------------------------------
        String factorial = ""
                + "N = 5\n"
                + "fact = 1\n"
                + "i = 1\n"
                + "while i <= N: {\n"
                + "    fact = fact * i\n"
                + "    i = i + 1\n"
                + "}\n"
                + "print(fact)\n";

        runSnippet(factorial);
        System.out.println("-----");

        // ------------------------------------------------
        // 3. GCD of two numbers (a=48, b=18)
        // Euclidean Algorithm
        // ------------------------------------------------
        String gcd = ""
                + "a = 48\n"
                + "b = 18\n"
                + "while b != 0: {\n"
                + "    temp = b\n"
                + "    b = a % b\n"
                + "    a = temp\n"
                + "}\n"
                + "print(a)\n";

        runSnippet(gcd);
        System.out.println("-----");

        // ------------------------------------------------
        // 4. Reverse a number (n=1234 => 4321)
        // ------------------------------------------------
        String reverseNumber = ""
                + "n = 1234\n"
                + "rev = 0\n"
                + "while n > 0: {\n"
                + "    digit = n % 10\n"
                + "    rev = rev * 10 + digit\n"
                + "    n = n / 10\n"
                + "}\n"
                + "print(rev)\n";

        runSnippet(reverseNumber);
        System.out.println("-----");

        // More tests can be added here...
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




