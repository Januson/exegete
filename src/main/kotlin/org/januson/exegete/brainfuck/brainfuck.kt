package org.januson.exegete.brainfuck

class BrainFuckRuntime(
        private val interpreter: Interpreter,
        private val program: Program
) {

    fun run() {

    }

}

class BrainFuckProgram(private val code: String) {}

interface Program {}

interface Interpreter {

    fun interpret(command: Char): Command

}

interface Tape {

    fun next()

    fun previous()

    fun increment()

    fun decrement()

}

interface Input {

    fun read(): Char

}

interface Output {

    fun write(char: Char)

}