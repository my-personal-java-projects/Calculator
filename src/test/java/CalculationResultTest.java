import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Unit tests for CalculationResult.

class CalculationResultTest {

    @Test
    void getResult_shouldReturnStoredResult() {
        CalculationResult result = new CalculationResult(10, Operation.ADD, 5, 15);
        assertEquals(15, result.getResult());
    }

    @Test
    void getOperands_shouldReturnStoredValues() {
        CalculationResult result = new CalculationResult(8, Operation.MULTIPLY, 3, 24);
        assertEquals(8, result.getOperandA());
        assertEquals(3, result.getOperandB());
    }

    @Test
    void getOperation_shouldReturnStoredOperation() {
        CalculationResult result = new CalculationResult(10, Operation.DIVIDE, 2, 5);
        assertEquals(Operation.DIVIDE, result.getOperation());
    }

    @Test
    void toString_wholeNumbers_shouldNotShowDecimalPoint() {
        CalculationResult result = new CalculationResult(10, Operation.ADD, 5, 15);
        String output = result.toString();
        assertFalse(output.contains("."),
                "Whole number result should not show decimal: " + output);
    }

    @Test
    void toString_decimalResult_shouldShowDecimal() {
        CalculationResult result = new CalculationResult(10, Operation.DIVIDE, 3, 10.0 / 3);
        String output = result.toString();
        assertTrue(output.contains("."));
    }

    @Test
    void toString_shouldContainOperatorSymbol() {
        CalculationResult result = new CalculationResult(4, Operation.MULTIPLY, 3, 12);
        assertTrue(result.toString().contains("*"));
    }

    @Test
    void toString_shouldContainAllComponents() {
        CalculationResult result = new CalculationResult(20, Operation.SUBTRACT, 8, 12);
        String output = result.toString();
        assertTrue(output.contains("20"));
        assertTrue(output.contains("-"));
        assertTrue(output.contains("8"));
        assertTrue(output.contains("12"));
    }
}
