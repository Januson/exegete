package org.januson.exegete.brainfuck

import org.testng.Assert.*
import org.testng.annotations.Test

class BrainfuckTapeTest {

    @Test
    fun createsBrainfuckTapeInstanceWithGivenSize() {
        val memory = 1

        val tape = BrainfuckTape(memory)

        assertEquals(tape.size, memory)
    }

    @Test
    fun afterInstantiationPointerIsSetToZero() {
        val memory = 1
        val expected = 0

        val tape = BrainfuckTape(memory)

        assertEquals(tape.pointer, expected)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun throwsExceptionWhenIllegalSizeIsProvided() {
        val memory = 0

        BrainfuckTape(memory)
    }

    @Test
    fun movesPointerToTheNextCell() {
        val memory = 2
        val expected = 1
        val tape = BrainfuckTape(memory)

        tape.next()

        assertEquals(tape.pointer, expected)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun throwsExceptionWhenPointerMovesPastTapeSize() {
        val memory = 1
        val tape = BrainfuckTape(memory)

        tape.next()
    }

    @Test
    fun movesPointerToThePreviousCell() {
        val memory = 2
        val start = 1
        val expected = 0
        val tape = BrainfuckTape(memory)
        tape.next()
        assertEquals(tape.pointer, start)

        tape.previous()

        assertEquals(tape.pointer, expected)
    }

    @Test(expectedExceptions = arrayOf(PointerOutOfBoundsException::class))
    fun throwsExceptionWhenPointerMovesPastZero() {
        val memory = 1
        val tape = BrainfuckTape(memory)

        tape.previous()
    }

    @Test
    fun memoryCellsInitialValueIsZero() {
        val memory = 1
        val expected = 0

        val tape = BrainfuckTape(memory)

        assertEquals(tape.cell, expected)
    }

    @Test
    fun increasesValueOfFistMemoryCellByOne() {
        val memory = 1
        val expected = 1
        val tape = BrainfuckTape(memory)

        tape.increment()

        assertEquals(tape.cell, expected)
    }

    @Test
    fun decreasesValueOfFistMemoryCellByOne() {
        val memory = 1
        val expected = 1
        val tape = BrainfuckTape(memory)
        tape.increment()
        tape.increment()

        tape.decrement()

        assertEquals(tape.cell, expected)
    }

    @Test
    fun resetSetsPointerToZero() {
        val memory = 3
        val expected = 0
        val tape = BrainfuckTape(memory)
        tape.next()
        tape.next()

        tape.reset()

        assertEquals(tape.pointer, expected)
    }

    @Test
    fun resetSetsAllMemoryCellsToZero() {
        val memory = 3
        val expected = 0
        val tape = BrainfuckTape(memory)
        tape.increment()
        tape.next()
        tape.increment()
        tape.next()
        tape.increment()

        tape.reset()

        assertEquals(tape.cell, expected)
        tape.next()
        assertEquals(tape.cell, expected)
        tape.next()
        assertEquals(tape.cell, expected)
    }
}