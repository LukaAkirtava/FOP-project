public class Lexer {
    private String input;
    private int position = 0;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.currentChar = input.charAt(position);
    }
}