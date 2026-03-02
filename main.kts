enum class Command(val label: String) {
    SUM("sum"),
    CONVERSION("conversion"),
    FACTORIAL("factorial"),
    EXIT("exit");

    companion object {
        fun fromInput(input: String): Command? =
            entries.find { it.label.equals(input.trim(), ignoreCase = true) }
    }
}

enum class TemperatureUnit(val label: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin");

    companion object {
        fun fromInput(input: String): TemperatureUnit? =
            entries.find { it.label.equals(input.trim(), ignoreCase = true) }

        fun allLabels(): String =
            entries.joinToString(", ") { it.label }
    }
}

// --- Command Handler ---

fun executeCommand(command: Command) {

    when (command) {

        Command.SUM -> throw NotImplementedError("sum not implemented")

        Command.CONVERSION -> {
            println("Enter temperature value:")
            val value = readln().toDoubleOrNull()
                ?: throw IllegalArgumentException("Invalid number.")

            println("Enter FROM unit (${TemperatureUnit.allLabels()}):")
            val from = TemperatureUnit.fromInput(readln())
                ?: throw IllegalArgumentException("Invalid source unit.")

            println("Enter TO unit (${TemperatureUnit.allLabels()}):")
            val to = TemperatureUnit.fromInput(readln())
                ?: throw IllegalArgumentException("Invalid target unit.")

            val result: Double = when (from to to) {

                TemperatureUnit.CELSIUS to TemperatureUnit.FAHRENHEIT ->
                    value * 9.0 / 5.0 + 32.0

                TemperatureUnit.CELSIUS to TemperatureUnit.KELVIN ->
                    value + 273.15

                TemperatureUnit.FAHRENHEIT to TemperatureUnit.CELSIUS ->
                    (value - 32.0) * 5.0 / 9.0

                TemperatureUnit.FAHRENHEIT to TemperatureUnit.KELVIN ->
                    (value - 32.0) * 5.0 / 9.0 + 273.15

                TemperatureUnit.KELVIN to TemperatureUnit.CELSIUS ->
                    value - 273.15

                TemperatureUnit.KELVIN to TemperatureUnit.FAHRENHEIT ->
                    (value - 273.15) * 9.0 / 5.0 + 32.0

                else -> value
            }

            println("Result: ${"%.2f".format(result)} ${to.label}")
        }

        Command.FACTORIAL -> {
            fun factorial(n: Int): Double {
                require(n in 0..20) { "Input must be between 0 and 20." }
                var result = 1.0
                for (i in 1..n) result *= i
                return result
            }

            println("Enter first number:")
            val n1 = readln().toIntOrNull()
                ?: throw IllegalArgumentException("Invalid number.")

            println("Enter second number:")
            val n2 = readln().toIntOrNull()
                ?: throw IllegalArgumentException("Invalid number.")

            println("Enter third number:")
            val n3 = readln().toIntOrNull()
                ?: throw IllegalArgumentException("Invalid number.")

            val total = factorial(n1) + factorial(n2) + factorial(n3)

            println("Result: ${"%.2f".format(total)}")
        }

        Command.EXIT -> println("Goodbye!")
    }
}

// --- Main Loop ---

println("=== Interactive Kotlin Command-Line Utility ===")
println("Commands: sum, conversion, factorial, exit\n")

var running = true

while (running) {

    print("> Enter command: ")

    val input = readlnOrNull()?.trim() ?: break
    val command = Command.fromInput(input)

    if (command == null) {
        println("Unknown command '$input'. Available: sum, conversion, factorial, exit\n")
        continue
    }

    if (command == Command.EXIT) {
        executeCommand(command)
        running = false
        continue
    }

    try {
        executeCommand(command)
    } catch (e: NotImplementedError) {
        println("This command is not yet implemented.")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }

    println()
}
