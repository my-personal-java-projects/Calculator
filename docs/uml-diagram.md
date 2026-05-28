# UML Class Diagram — Java Calculator

```
┌─────────────────────────────────────┐
│               <<main>>              │
│                Main                 │
├─────────────────────────────────────┤
│ + main(args: String[]) : void       │
└──────────────────┬──────────────────┘
                   │ creates
                   ▼
┌─────────────────────────────────────────┐
│               CalculatorUI              │
├─────────────────────────────────────────┤
│ - calculator : Calculator               │
│ - scanner    : Scanner                  │
├─────────────────────────────────────────┤
│ + start()              : void           │
│ - processExpression()  : void           │
│ - printHistory()       : void           │
│ - printBanner()        : void           │
└──────────────────┬──────────────────────┘
                   │ uses
                   ▼
┌─────────────────────────────────────────┐
│               Calculator                │
├─────────────────────────────────────────┤
│ - history : List<CalculationResult>     │
├─────────────────────────────────────────┤
│ + calculate(a, op, b) : CalculationResult│
│ + getHistory()        : List<...>       │
│ + clearHistory()      : void            │
└──────────┬──────────────────┬───────────┘
           │ returns          │ uses
           ▼                  ▼
┌──────────────────────┐  ┌──────────────────────┐
│  CalculationResult   │  │  <<enumeration>>      │
├──────────────────────┤  │  Operation            │
│ - operandA : double  │  ├──────────────────────┤
│ - operandB : double  │  │  ADD       (+)        │
│ - operation: Operation│ │  SUBTRACT  (-)        │
│ - result   : double  │  │  MULTIPLY  (*)        │
├──────────────────────┤  │  DIVIDE    (/)        │
│ + getResult()        │  │  MODULO    (%)        │
│ + getOperandA()      │  ├──────────────────────┤
│ + getOperandB()      │  │ - symbol : String     │
│ + getOperation()     │  ├──────────────────────┤
│ + toString()         │  │ + getSymbol() : String│
└──────────────────────┘  │ + fromSymbol() : Op   │
                          └──────────────────────┘
```

## Relationships Summary

| From | To | Type | Description |
|---|---|---|---|
| Main | CalculatorUI | Dependency | creates and calls `start()` |
| CalculatorUI | Calculator | Composition | owns a `Calculator` instance |
| Calculator | CalculationResult | Dependency | creates and stores results |
| Calculator | Operation | Dependency | switches on operation type |
| CalculatorUI | Operation | Dependency | calls `Operation.fromSymbol()` |
| CalculationResult | Operation | Association | holds the operation performed |
