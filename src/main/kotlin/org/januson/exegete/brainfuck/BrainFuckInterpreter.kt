package org.januson.exegete.brainfuck

import java.io.PrintStream
import java.util.*

/**
 * Class runs Brainfuck code provided as string.
 */
class BrainFuckInterpreter(val tape: Tape, val output: Output, val input: Input) : Interpreter {

    private var index = 0

    override fun interpret(command: Char): Command {
        when (command) {
            '>' -> {
            }
            '<' -> {
            }
            '+' -> {
            }
            '-' -> {
            }
            '.' -> {
            }
            ',' -> {
            }
            '[' -> {
//                if (tape.cell == 0) {
//                    var level = 1
//                    for (c in code.substring(i + 1)) {
//                        index++
//                        if (c == '[') {
//                            level++
//                        } else if (c == ']') {
//                            level--
//                        }
//                        if (level == 0) {
//                            return
//                        }
//                    }
//                }
            }
            ']' -> {
//                if (tape.cell > 0) {
//                    var level = 1
//                    for (c in code.substring(0, i).reversed()) {
//                        index--
//                        if (c == ']') {
//                            level++
//                        } else if (c == '[') {
//                            level--
//                        }
//                        if (level == 0) {
//                            return
//                        }
//                    }
//                }
            }
        }

        throw IllegalStateException()
    }
}