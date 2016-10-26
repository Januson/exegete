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
    private var index = 0

    init {
        if (memorySize <= 0) {
            throw IllegalArgumentException("Allocated memory must be greater then zero")
        }
    }

    fun interpret(code: String) {
        index = 0
        while (index < code.length) {
            interpret(index, code)
            index++
        }
        output.flush()
    }

    private fun interpret(i: Int, code: String) {
        val command = code[i]
        when (command) {
            '>' -> {
                if (pointer == memorySize - 1) {
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
            '[' -> {
                if (memory[pointer] == 0.toByte()) {
                    var level = 1
                    for (c in code.substring(i + 1)) {
                        index++
                        if (c == '[') {
                            level++
                        } else if (c == ']') {
                            level--
                        }
                        if (level == 0) {
                            return
                        }
                    }
                }
            }
            ']' -> {
                if (memory[pointer] > 0.toByte()) {
                    var level = 1
                    for (c in code.substring(0, i).reversed()) {
                        index--
                        if (c == ']') {
                            level++
                        } else if (c == '[') {
                            level--
                        }
                        if (level == 0) {
                            return
                        }
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    val bf = BrainfuckInterpreter(1, System.out, Scanner(System.`in`))
    bf.interpret(">")
    bf.interpret(".")
}