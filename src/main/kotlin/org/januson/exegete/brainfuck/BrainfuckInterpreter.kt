package org.januson.exegete.brainfuck

import java.io.PrintStream
import java.util.*

/**
 * Created by Januson on 13.10.2016.
 */
class BrainfuckInterpreter(val memorySize: Int, val output: PrintStream, val reader: Scanner) {

    private val memory = ByteArray(memorySize)
    var pointer = 0
        private set

    init {
        if (memorySize <= 0) {
            throw IllegalArgumentException("Allocated memory must be greater then zero")
        }
    }

    fun interpret(code: String) {
        code.forEach { interpret(it) }
        output.flush()
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
            '-' -> {
                memory[pointer]--
            }
            '.' -> {
                output.write(memory[pointer].toInt())
            }
            ',' -> {
                memory[pointer] = reader.nextByte()
            }
        }
    }
}
