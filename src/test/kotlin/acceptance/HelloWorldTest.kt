package acceptance

import org.januson.exegete.brainfuck.*
import org.testng.Assert
import org.testng.annotations.Test


class HelloWorldTest {

    @Test
    fun runsAHelloWorldProgram() {
        val tape = BrainFuckTape(256)
        val output = SpyOutput()
        val input = StubInput()
        val interpreter = BrainFuckInterpreter(tape, output, input)
        val program = BrainFuckRuntime(interpreter, BrainFuckProgram(HELLO_WORLD))

        program.run()

        Assert.assertEquals(output.output, "Hello, World!")
    }

    private companion object {
        private const val HELLO_WORLD = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
    }

}

class SpyOutput : Output {

    var output = ""
        private set

    override fun write(char: Char) {
        output += char
    }

}

class FakeInput(private val input: String) : Input {

    private var index = 0

    override fun read(): Char {
        return input[index++]
    }
}

class StubInput : Input {

    override fun read(): Char {
        throw IllegalStateException()
    }
}