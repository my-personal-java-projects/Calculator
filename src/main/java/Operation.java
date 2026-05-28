/**
 * Supported arithmetic operations.
 */
public enum Operation {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() { return symbol; }

    /** Parses a symbol string into an Operation. */
    public static Operation fromSymbol(String symbol) {
        for (Operation op : values()) {
            if (op.symbol.equals(symbol)) return op;
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }
}
