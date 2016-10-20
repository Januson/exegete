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
    var pointer = 0
        private set

    fun interpret(code: String) {
        code.forEach { interpret(it) }
    }

    private fun interpret(command: Char) {
        println(command)
        when(command) {
            '>' -> pointer++
            '<' -> pointer--
        }
    }
}

fun main(args: Array<String>) {
    val interpreter = BrainfuckInterpreter(2048)
    interpreter.interpret("+++++>>>")
}