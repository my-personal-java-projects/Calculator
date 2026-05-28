import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Calculator.
 */
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ── Arithmetic ───────────────────────────────────────────────────────────

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({"10, 5, 15", "0, 0, 0", "-3, 3, 0", "100, 200, 300"})
    void calculate_addition_shouldReturnCorrectResult(double a, double b, double expected) {
        CalculationResult result = calculator.calculate(a, Operation.ADD, b);
        assertEquals(expected, result.getResult(), 0.0001);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({"10, 5, 5", "0, 0, 0", "3, 10, -7", "100, 100, 0"})
    void calculate_subtraction_shouldReturnCorrectResult(double a, double b, double expected) {
        CalculationResult result = calculator.calculate(a, Operation.SUBTRACT, b);
        assertEquals(expected, result.getResult(), 0.0001);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({"3, 4, 12", "0, 999, 0", "-2, 5, -10", "7, 7, 49"})
    void calculate_multiplication_shouldReturnCorrectResult(double a, double b, double expected) {
        CalculationResult result = calculator.calculate(a, Operation.MULTIPLY, b);
        assertEquals(expected, result.getResult(), 0.0001);
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({"10, 2, 5", "9, 3, 3", "7, 2, 3.5", "0, 5, 0"})
    void calculate_division_shouldReturnCorrectResult(double a, double b, double expected) {
        CalculationResult result = calculator.calculate(a, Operation.DIVIDE, b);
        assertEquals(expected, result.getResult(), 0.0001);
    }

    @ParameterizedTest(name = "{0} % {1} = {2}")
    @CsvSource({"10, 3, 1", "15, 5, 0", "7, 4, 3"})
    void calculate_modulo_shouldReturnCorrectResult(double a, double b, double expected) {
        CalculationResult result = calculator.calculate(a, Operation.MODULO, b);
        assertEquals(expected, result.getResult(), 0.0001);
    }

    // ── Division by zero ─────────────────────────────────────────────────────

    @Test
    void calculate_divideByZero_shouldThrowArithmeticException() {
        assertThrows(ArithmeticException.class,
                () -> calculator.calculate(10, Operation.DIVIDE, 0));
    }

    @Test
    void calculate_moduloByZero_shouldThrowArithmeticException() {
        assertThrows(ArithmeticException.class,
                () -> calculator.calculate(10, Operation.MODULO, 0));
    }

    @Test
    void calculate_divideByZero_exceptionMessageShouldMentionZero() {
        Exception ex = assertThrows(ArithmeticException.class,
                () -> calculator.calculate(5, Operation.DIVIDE, 0));
        assertTrue(ex.getMessage().toLowerCase().contains("zero"));
    }

    // ── History ──────────────────────────────────────────────────────────────

    @Test
    void getHistory_initiallyEmpty() {
        assertTrue(calculator.getHistory().isEmpty());
    }

    @Test
    void getHistory_shouldGrowWithEachCalculation() {
        calculator.calculate(1, Operation.ADD, 1);
        calculator.calculate(2, Operation.ADD, 2);
        assertEquals(2, calculator.getHistory().size());
    }

    @Test
    void getHistory_shouldNotRecordFailedCalculations() {
        try { calculator.calculate(1, Operation.DIVIDE, 0); } catch (ArithmeticException ignored) {}
        assertTrue(calculator.getHistory().isEmpty());
    }

    @Test
    void getHistory_shouldBeUnmodifiable() {
        calculator.calculate(1, Operation.ADD, 1);
        assertThrows(UnsupportedOperationException.class,
                () -> calculator.getHistory().clear());
    }

    @Test
    void clearHistory_shouldEmptyTheList() {
        calculator.calculate(5, Operation.ADD, 5);
        calculator.calculate(10, Operation.MULTIPLY, 2);
        calculator.clearHistory();
        assertTrue(calculator.getHistory().isEmpty());
    }

    @Test
    void getHistory_recordShouldMatchCalculation() {
        CalculationResult result = calculator.calculate(6, Operation.MULTIPLY, 7);
        assertEquals(42, calculator.getHistory().get(0).getResult(), 0.0001);
        assertEquals(42, result.getResult(), 0.0001);
    }
}
