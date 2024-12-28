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
    /**
      Tokenizes the input source code into a list of tokens.
      Processes each character in the source string, identifying its type,
      and creating corresponding tokens for operators, numbers, identifiers, and more...
     */
    public List<Token> tokenize() {
        // Loop through the source code until reaching the end.
        while (!isAtEnd()) {
            // Read the current character and advance the position.
            char c = advance();
            switch (c) {
                // Handle single-character tokens.
                case '+': addToken(TokenType.PLUS, "+"); break;
                case '-': addToken(TokenType.MINUS, "-"); break;
                case '*': addToken(TokenType.STAR, "*"); break;
                case '/': addToken(TokenType.SLASH, "/"); break;
                case '%': addToken(TokenType.MOD, "%"); break;
                case '(': addToken(TokenType.LPAREN, "("); break;
                case ')': addToken(TokenType.RPAREN, ")"); break;
                case '{': addToken(TokenType.LBRACE, "{"); break;
                case '}': addToken(TokenType.RBRACE, "}"); break;
                case ':': addToken(TokenType.COLON, ":"); break;

                // Handle multi-character tokens.
                case '=':
                    if (match('=')) { // Check if the next character is '=' for '=='.
                        addToken(TokenType.EQEQ, "==");
                    } else { // Otherwise, it's a single '='.
                        addToken(TokenType.EQ, "=");
                    }
                    break;

                case '!':
                    if (match('=')) { // Check if the next character is '=' for '!='.
                        addToken(TokenType.NEQ, "!=");
                    }
                    // If it's a lone '!', ignore it for now or handle it as an error later.
                    break;

                case '>':
                    if (match('=')) { // Check if the next character is '=' for '>='.
                        addToken(TokenType.GTE, ">=");
                    } else { // Otherwise, it's a single '>'.
                        addToken(TokenType.GT, ">");
                    }
                    break;

                case '<':
                    if (match('=')) { // Check if the next character is '=' for '<='.
                        addToken(TokenType.LTE, "<=");
                    } else { // Otherwise, it's a single '<'.
                        addToken(TokenType.LT, "<");
                    }
                    break;

                // Handle whitespace characters.
                case ' ':
                case '\r':
                case '\t':
                case '\n':
                    // Ignore all whitespace.
                    break;

                // Handle numbers, identifiers, and unknown characters.
                default:
                    if (isDigit(c)) { // If the character is a digit, process a number token.
                        number(c);
                    } else if (isAlpha(c)) { // If the character is alphabetic, process an identifier.
                        identifier(c);
                    }
                    // Unknown characters are ignored for simplicity and no unnecessary errors .
                    break;
            }
        }
        // Add an EOF (End of File) token at the end of the input.
        addToken(TokenType.EOF, "");
        return tokens;
    }

    /**
     * Checks if we've reached the end of the source string.
     *
     * @return true if current >= length, otherwise false.
     */
    private boolean isAtEnd() {
        return current >= length;
    }

    /**
     * Advances the current index and returns the character at the old position.
     *
     * @return The character at the old position before incrementing.
     */
    private char advance() {
        return source.charAt(current++);
    }

    /**
     * Adds a new Token to the tokens list.
     *
     * @param type The type of the token (e.g., PLUS, NUMBER, IDENT, etc.).
     * @param text The text associated with the token (the actual substring).
     */
    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }

    /**
     * Checks if the next character matches the expected one.
     * If it matches, consume it and return true; otherwise return false.
     *
     * @param expected The character to check against.
     * @return true if the next character matches; false otherwise.
     */
    private boolean match(char expected) {
        // If we're already at the end, there's no char to match.
        if (isAtEnd()) return false;

        // If the next character doesn't match, return false.
        if (source.charAt(current) != expected) return false;

        // Otherwise, it's a match—consume it.
        current++;
        return true;
    }

    /**
     * Checks if a character is a digit (0-9).
     *
     * @param c The character to check.
     * @return true if c is between '0' and '9'; false otherwise.
     */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Handles parsing a number token from the current position.
     * Continues consuming characters as long as they are digits.
     *
     * @param firstChar The first digit character.
     */
    private void number(char firstChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstChar);

        // Keep reading as long as the next characters are digits.
        while (!isAtEnd() && isDigit(peek())) {
            sb.append(advance());
        }

        // Add the completed number token to the list.
        addToken(TokenType.NUMBER, sb.toString());
    }

    /**
     * Returns the next character without consuming it.
     *
     * @return The next character, or '\0' if at the end.
     */
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    /**
     * Checks if a character is alphabetic (a-z, A-Z) or underscore '_'.
     *
     * @param c The character to check.
     * @return true if the character is a letter or underscore; false otherwise.
     */
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    /**
     * Handles parsing an identifier or keyword.
     * Continues consuming characters as long as they are alphanumeric or underscore.
     *
     * @param firstChar The first character of the identifier or keyword.
     */
    private void identifier(char firstChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstChar);

        // Keep reading as long as the next characters are alphanumeric or underscore.
        while (!isAtEnd() && (isAlphaNumeric(peek()))) {
            sb.append(advance());
        }

        // Convert the built string to a known keyword token or default to an identifier.
        String text = sb.toString();
        TokenType type = checkKeyword(text);

        // Add the identified token.
        addToken(type, text);
    }

    /**
     * Checks if a character is alphanumeric (letter or digit) or underscore.
     *
     * @param c The character to check.
     * @return true if the character is alphanumeric or underscore; false otherwise.
     */
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    /**
     * Determines if the given text corresponds to a reserved keyword
     * or if it's just an identifier.
     *
     * @param text The string to check.
     * @return The appropriate TokenType (e.g. IF, ELSE, WHILE, PRINT, or IDENT).
     */
    private TokenType checkKeyword(String text) {
        switch (text) {
            case "if":     return TokenType.IF;
            case "else":   return TokenType.ELSE;
            case "while":  return TokenType.WHILE;
            case "print":  return TokenType.PRINT;
            default:       return TokenType.IDENT;
        }
    }
}