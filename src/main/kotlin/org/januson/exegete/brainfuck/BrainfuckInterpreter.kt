package org.januson.exegete.brainfuck

import java.io.PrintStream
import java.util.*

/**
 * Class runs Brainfuck code provided as string.
 */
class BrainfuckInterpreter(val tape: BrainfuckTape, val output: PrintStream, val reader: Scanner) {

    private var index = 0

    fun run(code: String) {
        index = 0
        while (index < code.length) {
            interpret(index, code)
            index++
        }
    }

    private fun interpret(i: Int, code: String) {
        val command = code[i]
        when (command) {
            '>' -> {
                tape.next()
            }
            '<' -> {
                tape.previous()
            }
            '+' -> {
                tape.increment()
            }
            '-' -> {
                tape.decrement()
            }
            '.' -> {
                output.write(tape.cell)
                output.flush()
            }
            ',' -> {
                tape.cell = reader.nextInt()
            }
            '[' -> {
                if (tape.cell == 0) {
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
                if (tape.cell > 0) {
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