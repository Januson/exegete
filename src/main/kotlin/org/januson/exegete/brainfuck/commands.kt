package org.januson.exegete.brainfuck

interface Command {

    fun execute()

}

class Next(private val tape: Tape) : Command {

    override fun execute() {
        tape.next()
    }

}

class Previous(private val tape: Tape) : Command {

    override fun execute() {
        tape.previous()
    }

}

class Increment(private val tape: Tape) : Command {

    override fun execute() {
        tape.increment()
    }

}

class Decrement(private val tape: Tape) : Command {

    override fun execute() {
        tape.increment()
    }

}