package org.januson.exegete.brainfuck

/**
 * Thrown to indicate that a pointer moved outside of tapes size. The pointer is either negative or greater than or
 * equal to the size of the tape.
 */
class PointerOutOfBoundsException: Throwable()