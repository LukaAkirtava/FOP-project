import java.util.ArrayList;
import java.util.List;

/**
 * The Lexer class is responsible for converting a source string
 * into a list of Tokens. It scans character by character, grouping
 * them into meaningful tokens (numbers, identifiers, operators, etc.).
 */

class Lexer {
    // The raw input source code as a String.
    private final String source;

    // The total length of the source.
    private final int length;

    // The current position/index within the source string.
    private int current = 0;

    // A list to hold all the tokens as we create them.
    private List<Token> tokens = new ArrayList<>();

    /**
     * Constructs a new Lexer with the given source code.
     *
     * @param source The input string to tokenize.
     */
    Lexer(String source) {
        this.source = source;
        this.length = source.length();
    }
}