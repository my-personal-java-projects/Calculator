import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Operation enum.
 */
class OperationTest {

    @ParameterizedTest(name = "fromSymbol(\"{0}\") should return {1}")
    @CsvSource({
        "+, ADD",
        "-, SUBTRACT",
        "*, MULTIPLY",
        "/, DIVIDE",
        "%, MODULO"
    })
    void fromSymbol_shouldReturnCorrectOperation(String symbol, String expectedName) {
        Operation result = Operation.fromSymbol(symbol);
        assertEquals(expectedName, result.name());
    }

    @Test
    void fromSymbol_unknownSymbol_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Operation.fromSymbol("^"));
    }

    @Test
    void fromSymbol_emptyString_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> Operation.fromSymbol(""));
    }

    @ParameterizedTest(name = "Operation.{0} should have symbol \"{1}\"")
    @CsvSource({
        "ADD, +",
        "SUBTRACT, -",
        "MULTIPLY, *",
        "DIVIDE, /",
        "MODULO, %"
    })
    void getSymbol_shouldMatchExpected(String operationName, String expectedSymbol) {
        Operation op = Operation.valueOf(operationName);
        assertEquals(expectedSymbol, op.getSymbol());
    }
}
