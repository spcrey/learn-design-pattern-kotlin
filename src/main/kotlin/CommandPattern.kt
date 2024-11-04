package org.spcrey

object CommandPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val context = Context()
        context.execute()
        context.bindCommand(AlphaCommand())
        context.execute()
        context.bindCommand(BetaCommand())
        context.bindCommand {
            println("GammaCommand().execute()")
        }
        context.execute()
        context.unbindCommand()
        context.execute()
    }

    /**
     * Console Output:
     * AlphaCommand().execute()
     * GammaCommand().execute()
     */

    fun interface Command {
        fun execute()
    }

    class AlphaCommand : Command {
        override fun execute() {
            println("AlphaCommand().execute()")
        }
    }

    class BetaCommand : Command {
        override fun execute() {
            println("BetaCommand().execute()")
        }
    }

    class Context {
        private var command: Command? = null

        fun bindCommand(command: Command) {
            this.command = command
        }

        fun unbindCommand() {
            this.command = null
        }

        fun execute() {
            command?.execute()
        }
    }
}