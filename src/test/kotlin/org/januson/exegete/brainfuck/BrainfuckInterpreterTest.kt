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
}