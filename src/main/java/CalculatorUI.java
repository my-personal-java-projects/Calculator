import java.util.Scanner;

/**
 * Console UI for the Calculator. Handles all user interaction.
 */
public class CalculatorUI {

    private final Calculator calculator = new Calculator();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        printBanner();
        System.out.println("Operators: +  -  *  /  %");
        System.out.println("Commands:  'history' | 'clear' | 'exit'\n");

        while (true) {
            System.out.print("Enter expression (e.g. 10 + 5): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equals("history")) {
                printHistory();
            } else if (input.equals("clear")) {
                calculator.clearHistory();
                System.out.println("History cleared.\n");
            } else {
                processExpression(input);
            }
        }
        scanner.close();
    }

    private void processExpression(String input) {
        String[] tokens = input.split("\\s+");
        if (tokens.length != 3) {
            System.out.println("  Invalid format. Use: <number> <operator> <number>\n");
            return;
        }
        try {
            double a  = Double.parseDouble(tokens[0]);
            Operation op = Operation.fromSymbol(tokens[1]);
            double b  = Double.parseDouble(tokens[2]);

            CalculationResult result = calculator.calculate(a, op, b);
            System.out.println("  = " + result + "\n");

        } catch (NumberFormatException e) {
            System.out.println("  Invalid number. Please enter digits only.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("  Unknown operator. Use: + - * / %\n");
        } catch (ArithmeticException e) {
            System.out.println("  Math error: " + e.getMessage() + "\n");
        }
    }

    private void printHistory() {
        var history = calculator.getHistory();
        if (history.isEmpty()) {
            System.out.println("  No calculations yet.\n");
            return;
        }
        System.out.println("  --- Calculation History ---");
        for (int i = 0; i < history.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, history.get(i));
        }
        System.out.println();
    }

    private void printBanner() {
        System.out.println("=========================================");
        System.out.println("          JAVA CALCULATOR v1.0          ");
        System.out.println("=========================================\n");
    }
}
