import java.util.Scanner
import kotlin.math.pow

/**
 * Interactive Kotlin Command-Line Utility
 * This application processes user inputs to perform calculations and conversions.
 * 
 * Available Commands:
 * - sum: Calculates 1³ + 2³ + 3³ + ... + n³
 * - conversion: Converts temperature between Celsius, Kelvin, and Fahrenheit
 * - factorial: Calculates the sum of factorials of three integers
 * - exit: Exit the application
 */

fun main() {
    val scanner = Scanner(System.`in`)
    
    println("=" * 60)
    println("Interactive Kotlin Command-Line Utility")
    println("=" * 60)
    println("\nAvailable Commands:")
    println("  sum         - Calculate sum of cubes (1³ + 2³ + ... + n³)")
    println("  conversion  - Convert temperature between C, K, F")
    println("  factorial   - Calculate sum of factorials")
    println("  exit        - Exit the application")
    println("=" * 60)
    
    while (true) {
        print("\nEnter command: ")
        val input = scanner.nextLine().trim().lowercase()
        
        if (input.isEmpty()) {
            println("Error: Please enter a command.")
            continue
        }
        
        when (interpretCommand(input)) {
            "exit" -> {
                println("\nThank you for using the Kotlin Command-Line Utility!")
                break
            }
            "sum" -> executeSum(scanner)
            "conversion" -> executeConversion(scanner)
            "factorial" -> executeFactorial(scanner)
            else -> println("Error: Unknown command '$input'. Please use sum, conversion, factorial, or exit.")
        }
    }
    
    scanner.close()
}

/**
 * Interprets and validates the user command
 * @param command The user input command
 * @return The normalized command name
 */
fun interpretCommand(command: String): String {
    return when (command.lowercase().trim()) {
        "sum" -> "sum"
        "conversion", "convert", "temp" -> "conversion"
        "factorial", "fact" -> "factorial"
        "exit", "quit", "q" -> "exit"
        else -> "unknown"
    }
}

/**
 * Executes the sum command: calculates 1³ + 2³ + 3³ + ... + n³
 * @param scanner Scanner for user input
 */
fun executeSum(scanner: Scanner) {
    println("\n--- Sum of Cubes Calculator ---")
    print("Enter a natural number (n): ")
    
    try {
        val input = scanner.nextLine().trim()
        val n = input.toIntOrNull()
        
        if (n == null) {
            println("Error: Invalid input. Please enter a valid integer.")
            return
        }
        
        if (n <= 0) {
            println("Error: Please enter a natural number (positive integer greater than 0).")
            return
        }
        
        if (n > 10000) {
            println("Error: Number too large. Please enter a number less than or equal to 10000.")
            return
        }
        
        val result = calculateSumOfCubes(n)
        println("Result: 1³ + 2³ + 3³ + ... + $n³ = $result")
        
    } catch (e: Exception) {
        println("Error: An unexpected error occurred - ${e.message}")
    }
}

/**
 * Calculates the sum of cubes from 1³ to n³
 * Formula: (n * (n + 1) / 2)²
 * @param n The upper limit
 * @return The sum of cubes
 */
fun calculateSumOfCubes(n: Int): Long {
    // Using the mathematical formula: (n*(n+1)/2)²
    val sum = (n.toLong() * (n + 1) / 2)
    return sum * sum
}

/**
 * Executes the temperature conversion command
 * @param scanner Scanner for user input
 */
fun executeConversion(scanner: Scanner) {
    println("\n--- Temperature Converter ---")
    print("Enter temperature value: ")
    
    try {
        val tempInput = scanner.nextLine().trim()
        val temperature = tempInput.toDoubleOrNull()
        
        if (temperature == null) {
            println("Error: Invalid temperature value. Please enter a valid number.")
            return
        }
        
        print("Enter source unit (C/K/F): ")
        val sourceUnit = scanner.nextLine().trim().uppercase()
        
        if (sourceUnit !in listOf("C", "K", "F")) {
            println("Error: Invalid source unit. Please enter C (Celsius), K (Kelvin), or F (Fahrenheit).")
            return
        }
        
        print("Enter target unit (C/K/F): ")
        val targetUnit = scanner.nextLine().trim().uppercase()
        
        if (targetUnit !in listOf("C", "K", "F")) {
            println("Error: Invalid target unit. Please enter C (Celsius), K (Kelvin), or F (Fahrenheit).")
            return
        }
        
        if (sourceUnit == targetUnit) {
            println("Result: $temperature°$sourceUnit = $temperature°$targetUnit")
            return
        }
        
        val result = convertTemperature(temperature, sourceUnit, targetUnit)
        
        if (result == null) {
            println("Error: Invalid temperature conversion. Temperature below absolute zero.")
            return
        }
        
        println("Result: $temperature°$sourceUnit = %.2f°$targetUnit".format(result))
        
    } catch (e: Exception) {
        println("Error: An unexpected error occurred - ${e.message}")
    }
}

/**
 * Converts temperature from one unit to another
 * @param value The temperature value
 * @param fromUnit The source unit (C, K, or F)
 * @param toUnit The target unit (C, K, or F)
 * @return The converted temperature or null if invalid
 */
fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double? {
    // First convert to Celsius
    val celsius = when (fromUnit) {
        "C" -> value
        "K" -> {
            if (value < 0) return null // Kelvin cannot be negative
            value - 273.15
        }
        "F" -> (value - 32) * 5.0 / 9.0
        else -> return null
    }
    
    // Check for absolute zero violations
    if (celsius < -273.15) return null
    
    // Then convert from Celsius to target unit
    return when (toUnit) {
        "C" -> celsius
        "K" -> celsius + 273.15
        "F" -> celsius * 9.0 / 5.0 + 32
        else -> null
    }
}

/**
 * Executes the factorial command: calculates n1! + n2! + n3!
 * @param scanner Scanner for user input
 */
fun executeFactorial(scanner: Scanner) {
    println("\n--- Sum of Factorials Calculator ---")
    
    try {
        print("Enter first integer (n1): ")
        val n1Input = scanner.nextLine().trim()
        val n1 = n1Input.toIntOrNull()
        
        if (n1 == null) {
            println("Error: Invalid input for n1. Please enter a valid integer.")
            return
        }
        
        print("Enter second integer (n2): ")
        val n2Input = scanner.nextLine().trim()
        val n2 = n2Input.toIntOrNull()
        
        if (n2 == null) {
            println("Error: Invalid input for n2. Please enter a valid integer.")
            return
        }
        
        print("Enter third integer (n3): ")
        val n3Input = scanner.nextLine().trim()
        val n3 = n3Input.toIntOrNull()
        
        if (n3 == null) {
            println("Error: Invalid input for n3. Please enter a valid integer.")
            return
        }
        
        if (n1 < 0 || n2 < 0 || n3 < 0) {
            println("Error: Factorial is not defined for negative numbers. Please enter non-negative integers.")
            return
        }
        
        if (n1 > 20 || n2 > 20 || n3 > 20) {
            println("Error: Numbers too large. Please enter integers less than or equal to 20 to avoid overflow.")
            return
        }
        
        val factorial1 = calculateFactorial(n1)
        val factorial2 = calculateFactorial(n2)
        val factorial3 = calculateFactorial(n3)
        val sum = factorial1 + factorial2 + factorial3
        
        println("Result: $n1! + $n2! + $n3! = $factorial1 + $factorial2 + $factorial3 = $sum")
        
    } catch (e: Exception) {
        println("Error: An unexpected error occurred - ${e.message}")
    }
}

/**
 * Calculates the factorial of a number
 * @param n The number to calculate factorial for
 * @return The factorial value
 */
fun calculateFactorial(n: Int): Long {
    if (n == 0 || n == 1) return 1
    
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

/**
 * Extension function to repeat a string (for formatting)
 */
private operator fun String.times(n: Int): String {
    return this.repeat(n)
}
