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
}
