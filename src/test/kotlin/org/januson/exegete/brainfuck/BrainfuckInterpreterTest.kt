package org.januson.exegete.brainfuck

import org.mockito.Mockito.*
import org.testng.Assert.assertNotNull
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.io.PrintStream
import java.util.*


class BrainfuckInterpreterTest {
    private val tape = mock(BrainfuckTape::class.java)
    private val writer = mock(PrintStream::class.java)
    private val reader = mock(Scanner::class.java)
    private var interpreter = BrainfuckInterpreter(tape, writer, reader)


    @BeforeMethod
    fun setUp() {
        reset(tape, writer, reader)
        interpreter = BrainfuckInterpreter(tape, writer, reader)
    }

    @Test
    fun createsBrainfuckInterpreterInstance() {
        assertNotNull(interpreter)
    }

    @Test
    fun movesTapePointerToRight() {
        val code = ">"
        interpreter.run(code)

        verify(tape, times(1)).next()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun throwsExceptionWhenMovesPastTapeSize() {
        val code = ">"
        `when`(tape.next()).thenThrow(PointerOutOfBoundsException::class.java)

        interpreter.run(code)
    }

    @Test
    fun movesPointerToLeft() {
        val code = "<"
        interpreter.run(code)

        verify(tape, times(1)).previous()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun throwsExceptionWhenMovesLowerThanZero() {
        val code = "<"
        `when`(tape.previous()).thenThrow(PointerOutOfBoundsException::class.java)

        interpreter.run(code)
    }

    @Test
    fun increasesValueOfCurrentMemoryCell() {
        val repeat = 105
        val code = "+".repeat(repeat)

        interpreter.run(code)

        verify(tape, times(repeat)).increment()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun decreasesValueOfCurrentMemoryCell() {
        val repeat = 222
        val code = "-".repeat(repeat)

        interpreter.run(code)

        verify(tape, times(repeat)).decrement()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun writesOutValueOfCurrentMemoryCell() {
        val code = "."

        interpreter.run(code)

        verify(tape, times(1)).cell
        verify(writer, times(1)).write(anyInt())
        verify(writer, times(1)).flush()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun readsUserInputToCurrentMemoryCell() {
        val code = ","
        `when`(reader.nextInt()).thenReturn(105)

        interpreter.run(code)

        verify(tape, times(1)).cell = anyInt()
        verify(reader, times(1)).nextInt()
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun skipsBehindCorrespondingRightBracket() {
        val code = "[+++]"

        interpreter.run(code)

        verify(tape, times(1)).cell
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun skipsBehindCorrespondingRightBracketWithNestedBrackets() {
        val code = "[>[>>++[++++]]>>]"

        interpreter.run(code)

        verify(tape, times(1)).cell
        verifyNoMoreInteractions(tape, writer, reader)
    }

    @Test
    fun returnsToCorrespondingLeftBracket() {
        val code = "[-]"
        `when`(tape.cell).thenReturn(2, 1, 0)

        interpreter.run(code)

        verify(tape, times(3)).cell
        verify(tape, times(2)).decrement()
        verifyNoMoreInteractions(tape, writer, reader)
    }
}