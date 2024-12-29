import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * The Interpreter class processes a list of tokens and manages
 * the execution context (in this case, just integer variables).
 * It keeps track of its current position in the token list
 * and stores variables by name in a HashMap.
 */
public class Interpreter {

    // The complete list of tokens to be interpreted
    private final List<Token> tokens;

    // An integer index pointing to the "current" token in the tokens list
    private int current = 0;

    // A map to store variables by their names and associated integer values
    private final Map<String, Integer> variables = new HashMap<>();

    /**
     * Constructs an Interpreter with a given list of tokens.
     *
     * @param tokens The list of tokens this Interpreter will work with
     */
    public Interpreter(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * The main entry point to run interpretation.
     */
    public void interpret() {
        while (!isAtEnd()) {
            parseStatement();
        }
    }



    // ---------------------
    // 1. STATEMENT PARSING
    // ---------------------
    private void parseStatement() {
        // Look at the current token to decide what statement we have

        // 1) Assignment:  x = expr
        if (match(TokenType.IDENT)) {
            String varName = previous().text;
            consume(TokenType.EQ, "Expect '=' after variable name.");
            int value = evaluateExpression();
            variables.put(varName, value);
            // Statement done
            return;
        }

        // 2) Print:  print(expr)
        if (match(TokenType.PRINT)) {
            consume(TokenType.LPAREN, "Expect '(' after 'print'.");
            int value = evaluateExpression();
            consume(TokenType.RPAREN, "Expect ')' after expression in print.");
            System.out.println(value);
            return;
        }

        // 3) If:   if expr: { ... }
        if (match(TokenType.IF)) {
            int condition = evaluateExpression();
            consume(TokenType.COLON, "Expect ':' after if condition.");
            consume(TokenType.LBRACE, "Expect '{' to start block.");

            if (condition != 0) {
                // interpret the block
                interpretBlock();
            } else {
                // skip the block
                skipBlock();
            }
            return;
        }

        // 4) While:  while expr: { ... }
        if (match(TokenType.WHILE)) {
            int conditionStart = current;  // after matching WHILE
            int condition = evaluateExpression();
            consume(TokenType.COLON, "Expect ':' after while condition.");
            consume(TokenType.LBRACE, "Expect '{' to start block.");

            int blockStart = current;
            int blockEnd = findBlockEnd(blockStart);

            // If condition != 0, interpret block repeatedly
            while (condition != 0) {
                interpretBlockTokens(blockStart, blockEnd);
                // re-check condition
                resetTo(conditionStart);
                condition = evaluateExpression();
                consume(TokenType.COLON, "Expect ':' after while condition.");
                consume(TokenType.LBRACE, "Expect '{' to start block.");
            }
            // once done, skip past the block
            resetTo(blockEnd + 1); // move after '}'
            return;
        }

        // If none matched, just advance to avoid infinite loop or stuck parser
        advance();
    }

    // Interpret a block in-place, until the matching '}'.
    // Precondition: we've already consumed '{'.
    private void interpretBlock() {
        int blockStart = current;
        int blockEnd = findBlockEnd(blockStart);
        interpretBlockTokens(blockStart, blockEnd);
        // move pointer right after '}'
        resetTo(blockEnd + 1);
    }

    // Interpret the tokens from start (inclusive) to end (exclusive).
    private void interpretBlockTokens(int start, int end) {
        int savedCurrent = current;
        resetTo(start);

        while (current < end && !isAtEnd()) {
            parseStatement();
        }
        resetTo(savedCurrent);
    }

    // Skip a block by finding its end and jumping beyond it.
    private void skipBlock() {
        int blockEnd = findBlockEnd(current);
        resetTo(blockEnd + 1);
    }

    // Find index of the matching '}' at the same nesting level.
    private int findBlockEnd(int startIndex) {
        int depth = 0;
        for (int i = startIndex; i < tokens.size(); i++) {
            if (tokens.get(i).type == TokenType.LBRACE) {
                depth++;
            } else if (tokens.get(i).type == TokenType.RBRACE) {
                if (depth == 0) {
                    return i;  // found matching
                } else {
                    depth--;
                }
            }
        }
        return tokens.size() - 1; // fallback if braces are unbalanced
    }

    // ---------------------
    // 2. EXPRESSION PARSING
    // ---------------------
    //
    // In a typical parser, you'd have multiple levels:
    //    expression -> equality -> comparison -> addition -> multiplication -> factor
    //
    // For brevity, we’ll do a two-step chain here:
    //    evaluateExpression() => evaluateEquality() => evaluateComparison() => evaluateTerm() => evaluateFactor()
    //

    private int evaluateExpression() {
        // Start with equality (which in turn calls comparison, addition, etc.).
        return evaluateEquality();
    }

// --------------------
// EQUALITY:  ==, !=
// --------------------
    private int evaluateEquality() {
        int left = evaluateComparison();
        while (match(TokenType.EQEQ, TokenType.NEQ)) {
            Token op = previous();
            int right = evaluateComparison();
            boolean result = (op.type == TokenType.EQEQ)
                    ? (left == right)
                    : (left != right);
            left = result ? 1 : 0;  // convert boolean to 1 or 0
        }
        return left;
    }

// --------------------
// COMPARISON: <, <=, >, >=
// --------------------
    private int evaluateComparison() {
        int left = evaluateAddition();
        while (match(TokenType.LT, TokenType.LTE, TokenType.GT, TokenType.GTE)) {
            Token op = previous();
            int right = evaluateAddition();
            boolean result;
            switch (op.type) {
                case LT:  result = (left < right);  break;
                case LTE: result = (left <= right); break;
                case GT:  result = (left > right);  break;
                case GTE: result = (left >= right); break;
                default:
                    result = false; // Shouldn't happen
            }
            left = result ? 1 : 0;
        }
        return left;
    }
// --------------------
// ADDITION: +, -
// --------------------
    private int evaluateAddition() {
        int value = evaluateTerm();
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            int right = evaluateTerm();
            if (operator.type == TokenType.PLUS) {
                value += right;
            } else {
                value -= right;
            }
        }
        return value;
    }
// --------------------
// TERM: *, /, %
// --------------------
    private int evaluateTerm() {
        int value = evaluateFactor();
        while (match(TokenType.STAR, TokenType.SLASH, TokenType.MOD)) {
            Token operator = previous();
            int right = evaluateFactor();
            switch (operator.type) {
                case STAR:
                    value *= right;
                    break;
                case SLASH:
                    if (right == 0) {
                        throw new RuntimeException("Division by zero.");
                    }
                    value /= right;
                    break;
                case MOD:
                    if (right == 0) {
                        throw new RuntimeException("Division by zero in modulus.");
                    }
                    value %= right;
                    break;
            }
        }
        return value;
    }

    // --------------------
// FACTOR: number, ident, (expression)
// --------------------
    private int evaluateFactor() {
        if (match(TokenType.NUMBER)) {
            return Integer.parseInt(previous().text);
        }
        if (match(TokenType.IDENT)) {
            String varName = previous().text;
            return variables.getOrDefault(varName, 0);
        }
        if (match(TokenType.LPAREN)) {
            int val = evaluateExpression();
            consume(TokenType.RPAREN, "Expect ')' after expression.");
            return val;
        }
        throw new RuntimeException("Unexpected token in expression: " + peek());
    }
    // ---------------------
    // TOKEN UTILITIES
    // ---------------------
    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType type, String errorMessage) {
        if (check(type)) return advance();
        throw new RuntimeException(errorMessage + " Found: " + peek());
    }

    // Helper to reset the current pointer (used for while-block re-check).
    private void resetTo(int newIndex) {
        this.current = newIndex;
    }
}