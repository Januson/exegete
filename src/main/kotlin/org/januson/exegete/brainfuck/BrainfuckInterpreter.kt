package org.januson.exegete.brainfuck

import java.io.PrintStream

/**
 * Created by Januson on 13.10.2016.
 */
class BrainfuckInterpreter(val memorySize: Int, val printer: PrintStream) {

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
        printer.flush()
    }

    private fun interpret(command: Char) {
        when (command) {
            '>' -> {
                if (pointer == memorySize) {
                    throw PointerOutOfBoundsException()
                }
                pointer++
            }
            '<' -> {
                if (pointer == 0) {
                    throw PointerOutOfBoundsException()
                }
                pointer--
            }
            '+' -> {
                memory[pointer]++
            }
            '.' -> {
                printer.write(memory[pointer].toInt())
            }
        }
    }
}

fun main(args: Array<String>) {
    val bf = BrainfuckInterpreter(8, System.out)
    val a = "+".repeat(97)
    bf.interpret("%s.>%s+.>%s++.%n".format(a, a, a))
}