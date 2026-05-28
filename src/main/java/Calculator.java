import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Performs arithmetic calculations and maintains a history of results.
 */
public class Calculator {

    private final List<CalculationResult> history = new ArrayList<>();

    /**
     * Performs the given operation on two operands.
     *
     * @throws ArithmeticException if dividing or modulo by zero
     */
    public CalculationResult calculate(double a, Operation op, double b) {
        double result = switch (op) {
            case ADD      -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE   -> {
                if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
                yield a / b;
            }
            case MODULO   -> {
                if (b == 0) throw new ArithmeticException("Cannot modulo by zero.");
                yield a % b;
            }
        };

        CalculationResult calculationResult = new CalculationResult(a, op, b, result);
        history.add(calculationResult);
        return calculationResult;
    }

    /** Returns an unmodifiable view of the calculation history. */
    public List<CalculationResult> getHistory() {
        return Collections.unmodifiableList(history);
    }

    /** Clears all stored history. */
    public void clearHistory() {
        history.clear();
    }
}
