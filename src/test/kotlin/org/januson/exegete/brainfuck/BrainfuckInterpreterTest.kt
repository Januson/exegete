package org.januson.exegete.brainfuck

import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Created by januson on 10/15/16.
 */
class BrainfuckInterpreterTest {
    private val writer = System.out

    @Test
    fun createsBrainfuckInterpreterInstance() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer)

        assertEquals(interpreter.memorySize, memory)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun cannotCreateInterpreterWithZeroMemory() {
        val memory = 0
        BrainfuckInterpreter(memory, writer)
    }

    @Test
    fun movesPointerToRight() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer)
        interpreter.interpret(">")

        assertEquals(interpreter.pointer, 1)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun failsToMovePointerPastMemorySize() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer)
        interpreter.interpret(">>")
    }

    @Test
    fun movesPointerToLeft() {
        val memory = 1
        val interpreter = BrainfuckInterpreter(memory, writer)
        interpreter.interpret(">")
        assertEquals(interpreter.pointer, 1)

        interpreter.interpret("<")

        assertEquals(interpreter.pointer, 0)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun failsToMovePointerLowerThanZero() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer)
        interpreter.interpret("<")
    }

    @Test
    fun returnsValueOfCurrentMemoryCell() {
        val memory = 1
        val writer = ByteArrayOutputStream()
        val interpreter = BrainfuckInterpreter(memory, PrintStream(writer))

        interpreter.interpret(".")

        assertEquals(interpreter.pointer, 0)
        assertFalse(writer.toString() == "")
    }
}