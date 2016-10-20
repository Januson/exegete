package org.januson.exegete.brainfuck

/**
 * Created by Januson on 13.10.2016.
 */
class BrainfuckInterpreter(val memorySize: Int) {

    init {
        if (memorySize <= 0) {
            throw IllegalArgumentException("Allocated memory must be greater then zero")
        }
    }

    private val memory = ByteArray(memorySize)
    private var pointer = 0

    fun interpret(code: String) {
        code.forEach { interpret(it) }
    }

    private fun interpret(instruction: Char) {
        println(instruction)
    }
}

fun main(args: Array<String>) {
    val interpreter = BrainfuckInterpreter(2048)
    interpreter.interpret("+++++>>>")
}