# Java Calculator 

A console-based calculator supporting five arithmetic operations, session history, and graceful error handling. Built to demonstrate clean OOP design, enum-driven logic, exception handling, and comprehensive unit testing with JUnit 5.

---

## Project Overview

| Detail | Info |
|---|---|
| Language | Java 17+ |
| Build Tool | Maven |
| Testing | JUnit 5 (Jupiter) |
| Type | Console Application |
| Author | Your Name |

---

## Features

- Five operators: **+  −  ×  ÷  %**
- Division and modulo by zero handled gracefully with a clear error message
- Session history — type `history` to see all past calculations
- `clear` command wipes history; `exit` quits cleanly
- Whole-number results display without a trailing `.0` (e.g. `15` not `15.0`)
- Full input validation with user-friendly error messages

---

## Project Structure

```
calculator/
├── pom.xml                              ← Maven build + JUnit 5 dependency
├── README.md
├── docs/
│   └── uml-diagram.md                  ← UML class diagram
└── src/
    ├── main/
    │   └── java/
    │       ├── Main.java                ← Entry point
    │       ├── Calculator.java          ← Arithmetic engine + history
    │       ├── CalculatorUI.java        ← Console input/output
    │       ├── CalculationResult.java   ← Immutable result value object
    │       └── Operation.java           ← Enum of supported operators
    └── test/
        └── java/
            ├── CalculatorTest.java      ← 18 tests for arithmetic engine
            ├── CalculationResultTest.java ← 7 tests for result object
            └── OperationTest.java       ← 8 parameterised tests for enum
```

---

## 🚀 Running the Application in IntelliJ IDEA

### Step 1 — Open the project
1. Open IntelliJ IDEA
2. Click **File → Open**
3. Select the `calculator` folder (the one containing `pom.xml`)
4. IntelliJ will detect Maven automatically and download JUnit

### Step 2 — Set the Java SDK
1. Go to **File → Project Structure → Project**
2. Set **SDK** to Java 17 or higher
3. Click **Apply → OK**

### Step 3 — Run
- Right-click `Main.java` → **Run 'Main.main()'**
- The calculator starts in the terminal panel at the bottom

---

## Usage

```
=========================================
          JAVA CALCULATOR v1.0
=========================================

Operators: +  -  *  /  %
Commands:  'history' | 'clear' | 'exit'

Enter expression (e.g. 10 + 5): 20 * 3
  = 20 * 3 = 60

Enter expression (e.g. 10 + 5): 7 / 2
  = 7 / 2 = 3.5

Enter expression (e.g. 10 + 5): 10 / 0
  Math error: Cannot divide by zero.

Enter expression (e.g. 10 + 5): history
  --- Calculation History ---
  1. 20 * 3 = 60
  2. 7 / 2 = 3.5

Enter expression (e.g. 10 + 5): exit
Goodbye!
```

---

## Running the Tests

### In IntelliJ
- Right-click `src/test/java` → **Run 'All Tests'**
- Or open a `*Test.java` file and click ▶ next to any test

### With Maven (terminal)
```bash
# From inside the calculator folder:
mvn test

# Run one test class:
mvn test -Dtest=CalculatorTest

# Run one specific test:
mvn test -Dtest=CalculatorTest#calculate_divideByZero_shouldThrowArithmeticException
```

### Test Coverage Summary

| Test Class | Tests | What It Covers |
|---|---|---|
| `CalculatorTest` | 18 | Addition, subtraction, multiplication, division, modulo, divide-by-zero, history management |
| `CalculationResultTest` | 7 | Field storage, whole-number formatting, decimal formatting, toString content |
| `OperationTest` | 8 | `fromSymbol()` for all 5 operators, unknown symbol, `getSymbol()` round-trip |
| **Total** | **33** | |

---

## Class Responsibilities

### `Main`
Entry point. Creates and starts a `CalculatorUI`. No logic here.

### `Calculator`
The arithmetic engine. Performs calculations via a switch expression on `Operation` and stores each `CalculationResult` in an `ArrayList`. Failed calculations (e.g. divide-by-zero) are not stored. Exposes an unmodifiable view of history via `getHistory()`.

### `CalculatorUI`
Handles all console I/O. Parses the three-token expression format (`number operator number`), catches and displays all exceptions with friendly messages, and handles the `history`, `clear`, and `exit` commands.

### `CalculationResult`
An immutable snapshot of one calculation: both operands, the operation, and the result. `toString()` formats cleanly — whole numbers show without `.0`. The object is created by `Calculator` and stored in its history list.

### `Operation`
An enum of the five supported operators. Each value carries its symbol string. `fromSymbol(String)` is a factory that parses user input into an `Operation`, throwing `IllegalArgumentException` for unknown symbols.

---

## Key Java Concepts Demonstrated

| Concept | Where |
|---|---|
| Enums with fields and methods | `Operation` — stores symbol, exposes `fromSymbol()` factory |
| Switch expressions | `Calculator.calculate()` — one expression covers all 5 operators |
| Exception handling | `ArithmeticException`, `NumberFormatException`, `IllegalArgumentException` |
| Immutable value objects | `CalculationResult` |
| Collections | `ArrayList` + `Collections.unmodifiableList()` |
| Separation of concerns | Engine (`Calculator`) vs UI (`CalculatorUI`) vs data (`CalculationResult`) |
| Parameterised tests | `@ParameterizedTest` + `@CsvSource` in `CalculatorTest` and `OperationTest` |

---

## UML Diagram

See [`docs/uml-diagram.md`](docs/uml-diagram.md)

---

## Possible Extensions

- Support bracket expressions and operator precedence (e.g. `(2 + 3) * 4`)
- Add square root (`sqrt 9`) and power (`2 ^ 8`) operators
- Save and load history from a `.txt` file on exit
- Build a JavaFX GUI version with button input
- Parse multi-operator expressions using a stack-based evaluator
