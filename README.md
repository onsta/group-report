# Interactive Kotlin Command-Line Utility

## Project Overview
This is a Kotlin-based command-line application that processes user inputs to perform specific calculations and conversions. The application demonstrates procedural programming concepts with proper control flow, data handling, and comprehensive input validation.

## Features

The application provides an interactive command-line interface with the following commands:

### 1. Sum Command
- **Purpose**: Calculates the sum of cubes from 1³ to n³
- **Formula**: 1³ + 2³ + 3³ + ... + n³
- **Mathematical Formula Used**: (n × (n + 1) / 2)²
- **Example**: For n=5: 1³ + 2³ + 3³ + 4³ + 5³ = 1 + 8 + 27 + 64 + 125 = 225

### 2. Temperature Conversion Command
- **Purpose**: Converts temperatures between Celsius, Kelvin, and Fahrenheit
- **Supported Units**: C (Celsius), K (Kelvin), F (Fahrenheit)
- **Features**:
  - Validates temperature values against absolute zero
  - Supports bidirectional conversion between any two units
- **Example**: 100°C = 212°F

### 3. Factorial Sum Command
- **Purpose**: Calculates the sum of factorials of three integers
- **Formula**: n1! + n2! + n3!
- **Example**: 3! + 4! + 5! = 6 + 24 + 120 = 150

## Technical Implementation

### Architecture
- **Main Function**: `main()` - Controls the interactive loop and command interpretation
- **Command Interpreter**: `interpretCommand()` - Validates and normalizes user commands
- **Execution Functions**:
  - `executeSum()` - Handles sum command execution
  - `executeConversion()` - Handles temperature conversion
  - `executeFactorial()` - Handles factorial sum calculation
- **Calculation Functions**:
  - `calculateSumOfCubes()` - Implements optimized cube sum calculation
  - `convertTemperature()` - Performs temperature unit conversions
  - `calculateFactorial()` - Computes factorial values

### Input Validation
The application includes comprehensive input validation:
- **Sum Command**:
  - Validates natural numbers (positive integers > 0)
  - Prevents overflow with upper limit of 10,000
  - Handles invalid input gracefully

- **Temperature Conversion**:
  - Validates numeric temperature values
  - Ensures valid unit codes (C, K, F)
  - Checks for absolute zero violations
  - Prevents physically impossible temperatures

- **Factorial Command**:
  - Validates integer inputs
  - Rejects negative numbers
  - Prevents overflow with upper limit of 20
  - Handles all three inputs independently

### Error Handling
- Clear, user-friendly error messages
- Graceful handling of invalid inputs
- Try-catch blocks for unexpected errors
- Continuous operation despite errors

## Building and Running

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Gradle (wrapper included)

### Build the Project
```bash
./gradlew build
```

### Run the Application
```bash
./gradlew run
```

Or use the distribution:
```bash
./gradlew installDist
./build/install/group-report/bin/group-report
```

## Usage Examples

### Example Session
```
============================================================
Interactive Kotlin Command-Line Utility
============================================================

Available Commands:
  sum         - Calculate sum of cubes (1³ + 2³ + ... + n³)
  conversion  - Convert temperature between C, K, F
  factorial   - Calculate sum of factorials
  exit        - Exit the application
============================================================

Enter command: sum

--- Sum of Cubes Calculator ---
Enter a natural number (n): 5
Result: 1³ + 2³ + 3³ + ... + 5³ = 225

Enter command: conversion

--- Temperature Converter ---
Enter temperature value: 100
Enter source unit (C/K/F): C
Enter target unit (C/K/F): F
Result: 100.0°C = 212.00°F

Enter command: factorial

--- Sum of Factorials Calculator ---
Enter first integer (n1): 3
Enter second integer (n2): 4
Enter third integer (n3): 5
Result: 3! + 4! + 5! = 6 + 24 + 120 = 150

Enter command: exit

Thank you for using the Kotlin Command-Line Utility!
```

## Project Structure
```
group-report/
├── src/
│   └── main/
│       └── kotlin/
│           └── Main.kt          # Main application file
├── build.gradle.kts             # Gradle build configuration
├── settings.gradle.kts          # Gradle settings
├── .gitignore                   # Git ignore file
└── README.md                    # This file
```

## Code Quality Features
- Well-organized code structure
- Comprehensive documentation and comments
- Separation of concerns (command interpretation, execution, calculation)
- Efficient algorithms (using mathematical formulas where possible)
- Clear variable and function naming
- Consistent code formatting

## Learning Outcomes Demonstrated

### I. Procedural Programming Concepts
- Sequential execution flow
- Function decomposition
- Modular design with separate functions for each task
- Efficient use of loops and conditionals

### II. Object-Oriented Programming Facilities
- Proper use of Kotlin language features
- Type safety with proper type declarations
- Extension functions (String.times operator)
- Null safety and proper exception handling

### III. Complex Problem Solving
- Implementation of mathematical algorithms
- Temperature conversion logic
- Factorial calculations
- Input validation and error handling
- Interactive user interface design