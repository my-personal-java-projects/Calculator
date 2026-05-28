/**
 * Immutable value object holding the result of a single calculation.
 */
public class CalculationResult {

    private final double operandA;
    private final double operandB;
    private final Operation operation;
    private final double result;

    public CalculationResult(double operandA, Operation operation, double operandB, double result) {
        this.operandA  = operandA;
        this.operandB  = operandB;
        this.operation = operation;
        this.result    = result;
    }

    public double getResult()       { return result; }
    public double getOperandA()     { return operandA; }
    public double getOperandB()     { return operandB; }
    public Operation getOperation() { return operation; }

    @Override
    public String toString() {
        String a = formatNumber(operandA);
        String b = formatNumber(operandB);
        String r = formatNumber(result);
        return String.format("%s %s %s = %s", a, operation.getSymbol(), b, r);
    }

    /** Prints whole numbers without a decimal point. */
    private String formatNumber(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
