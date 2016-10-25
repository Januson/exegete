package org.januson.exegete.brainfuck

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.testng.Assert.assertEquals
import org.testng.Assert.assertFalse
import org.testng.annotations.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*


class BrainfuckInterpreterTest {
    private val writer = System.out
    private val reader = Scanner(System.`in`)

    @Test
    fun createsBrainfuckInterpreterInstance() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer, reader)

        assertEquals(interpreter.memorySize, memory)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun cannotCreateInterpreterWithZeroMemory() {
        val memory = 0
        BrainfuckInterpreter(memory, writer, reader)
    }

    @Test
    fun movesPointerToRight() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer, reader)
        interpreter.interpret(">")

        assertEquals(interpreter.pointer, 1)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun failsToMovePointerPastMemorySize() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer, reader)
        interpreter.interpret(">>")
    }

    @Test
    fun movesPointerToLeft() {
        val memory = 1
        val interpreter = BrainfuckInterpreter(memory, writer, reader)
        interpreter.interpret(">")
        assertEquals(interpreter.pointer, 1)

        interpreter.interpret("<")

        assertEquals(interpreter.pointer, 0)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun failsToMovePointerLowerThanZero() {
        val memory = 1

        val interpreter = BrainfuckInterpreter(memory, writer, reader)
        interpreter.interpret("<")
    }

    @Test
    fun increasesValueOfCurrentMemoryCellByNinetySeven() {
        val memory = 1
        val writer = ByteArrayOutputStream()
        val interpreter = BrainfuckInterpreter(memory, PrintStream(writer), reader)

        interpreter.interpret("+".repeat(97) + ".")

        assertEquals(writer.toString(), "a")
    }

    @Test
    fun decreasesValueOfCurrentMemoryCellByTwo() {
        val memory = 1
        val writer = ByteArrayOutputStream()
        val interpreter = BrainfuckInterpreter(memory, PrintStream(writer), reader)
        interpreter.interpret("+".repeat(100))

        interpreter.interpret("--.")
        assertEquals(writer.toString(), "b")
    }

    @Test
    fun returnsValueOfCurrentMemoryCell() {
        val memory = 1
        val writer = ByteArrayOutputStream()
        val interpreter = BrainfuckInterpreter(memory, PrintStream(writer), reader)

        interpreter.interpret(".")

        assertFalse(writer.toString() == "")
    }

    @Test
    fun writesUserInputToCurrentMemoryCell() {
        val memory = 1
        val writer = ByteArrayOutputStream()
        val reader = mock(Scanner::class.java)
        `when`(reader.nextByte()).thenReturn(105)
        val interpreter = BrainfuckInterpreter(memory, PrintStream(writer), reader)

        interpreter.interpret(",.")

        assertEquals(writer.toString(), "i")
    }
}