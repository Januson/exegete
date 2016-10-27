package org.januson.exegete.brainfuck

/**
 * This class represents <code>Brainfuck</code> memory memory. The <code>size</code> argument specifies the number of
 * tape's memory cells.
 *
 * @param size of the memory's memory.
 */
class BrainfuckTape(val size: Int) {

    /**
     * An array of memory cells.
     */
    private val memory: IntArray

    /**
     * This property is used to navigate tape's memory.
     */
    var pointer: Int
        private set


    /**
     * Virtual property. Used to access or set value to the memory cell currently pointed to by the pointer.
     */
    var cell: Int
        get() = memory[pointer]
        set(value) {
            memory[pointer] = value
        }


    /**
     * Constructor. Checks if valid memory size was provided. Initializes memory and pointer.
     */
    init {
        if (size <= 0) {
            throw IllegalArgumentException("Tape size must be greater than zero.")
        }
        memory = IntArray(size)
        pointer = 0
    }

    /**
     * Moves pointer to the next memory cell. Throws <code>PointerOutOfBoundsException</code> if pointer moves past
     * memory size.
     *
     * @throws PointerOutOfBoundsException when pointer moves past memory size.
     */
    fun next() {
        if (pointer == size - 1) {
            throw PointerOutOfBoundsException()
        }
        pointer++
    }

    /**
     * Moves pointer to the previous memory cell. Throws <code>PointerOutOfBoundsException</code> if pointer moves
     * lower than zero.
     *
     * @throws PointerOutOfBoundsException when pointer moves past zero
     */
    fun previous() {
        if (pointer == 0) {
            throw PointerOutOfBoundsException()
        }
        pointer--
    }

    /**
     * Increases value of memory cell currently pointed to by pointer by one.
     */
    fun increment() {
        memory[pointer]++
    }

    /**
     * Decreases value of memory cell currently pointed to by pointer by one.
     */
    fun decrement() {
        memory[pointer]--
    }

    /**
     * Resets pointer and memory to zero.
     */
    fun reset() {
        memory.fill(0)
        pointer = 0
    }
}