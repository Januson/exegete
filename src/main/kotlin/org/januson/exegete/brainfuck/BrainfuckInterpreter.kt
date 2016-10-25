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
            '.' -> {
                printer.write(memory[pointer].toInt())
                printer.flush()
            }
        }
    }
}