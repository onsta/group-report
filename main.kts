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

fun executeCommand(command: Command) {

    when (command) {

        Command.SUM -> {
            println("Enter a natural number:")
            val n = readln().toIntOrNull()
                ?: throw IllegalArgumentException("Invalid number.")

            if (n < 1)
                throw IllegalArgumentException("Number must be positive.")

            var total = 0L
            for (i in 1..n) {
                total += i * i * i
            }

            println("Result: $total")
        }

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

            val result = when (from to to) {

                TemperatureUnit.CELSIUS to TemperatureUnit.FAHRENHEIT ->
                    value * 9 / 5 + 32

                TemperatureUnit.CELSIUS to TemperatureUnit.KELVIN ->
                    value + 273.15

                TemperatureUnit.FAHRENHEIT to TemperatureUnit.CELSIUS ->
                    (value - 32) * 5 / 9

                TemperatureUnit.FAHRENHEIT to TemperatureUnit.KELVIN ->
                    (value - 32) * 5 / 9 + 273.15

                TemperatureUnit.KELVIN to TemperatureUnit.CELSIUS ->
                    value - 273.15

                TemperatureUnit.KELVIN to TemperatureUnit.FAHRENHEIT ->
                    (value - 273.15) * 9 / 5 + 32

                else -> value
            }

            println("Result: $result ${to.label}")
        }

        Command.FACTORIAL -> {
            fun factorial(n: Int): Long {
                var result = 1L
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

            if (n1 < 0 || n2 < 0 || n3 < 0)
                throw IllegalArgumentException("Numbers must be non-negative.")

            val total = factorial(n1) + factorial(n2) + factorial(n3)

            println("Result: $total")
        }

        Command.EXIT -> println("Goodbye!")
    }
}

println("=== Interactive Kotlin Command-Line Utility ===")
println("Commands: sum, conversion, factorial, exit\n")

var running = true

while (running) {

    print("> Enter command: ")

    val input = readlnOrNull()?.trim() ?: break
    val command = Command.fromInput(input)

    if (command == null) {
        println("Unknown command. Available: sum, conversion, factorial, exit\n")
        continue
    }

    if (command == Command.EXIT) {
        executeCommand(command)
        running = false
        continue
    }

    try {
        executeCommand(command)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }

    println()
}
