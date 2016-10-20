package org.januson.exegete.brainfuck

import org.testng.Assert.*
import org.testng.annotations.Test

/**
 * Created by januson on 10/15/16.
 */
class BrainfuckInterpreterTest {

    @Test
    fun createsBrainfuckInterpreterInstance() {
        val memory = 2048

        val interpreter = BrainfuckInterpreter(memory)

        assertEquals(interpreter.memorySize, memory)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun cannotCreateInterpreterWithZeroMemory() {
        val memory = 0
        BrainfuckInterpreter(memory)
    }

    @Test
    fun movesPointerToRight() {
        val memory = 2048

        val interpreter = BrainfuckInterpreter(memory)
        interpreter.interpret(">")
        
        assertEquals(interpreter.pointer, 1)
    }
}