import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Calculator {

    private final List<CalculationResult> history = new ArrayList<>();

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

    public List<CalculationResult> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public void clearHistory() {
        history.clear();
    }
}
