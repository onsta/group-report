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

        fun allLabels(): String = entries.joinToString(", ") { it.label }
    }
}

data class ConversionRequest(
    val value: Double,
    val from: TemperatureUnit,
    val to: TemperatureUnit
)

// --- Command Handler ---

fun executeCommand(command: Command) {
    when (command) {
        Command.SUM -> throw NotImplementedError("sum not implemented")
        Command.CONVERSION -> throw NotImplementedError("conversion not implemented")
        Command.FACTORIAL -> throw NotImplementedError("factorial not implemented")
        Command.EXIT -> println("Goodbye!")
    }
}

// --- Main Loop ---

println("=== Interactive Kotlin Command-Line Utility ===")
println("Commands: sum, conversion, factorial, exit\n")


while (true) {
    print("> Enter command: ")
    val input = readlnOrNull()?.trim() ?: break
    val command = Command.fromInput(input)

    if (command == null) {
        println("Unknown command '$input'. Available: sum, conversion, factorial, exit")
        continue
    }

    if (command == Command.EXIT) {
        executeCommand(command)
        break
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