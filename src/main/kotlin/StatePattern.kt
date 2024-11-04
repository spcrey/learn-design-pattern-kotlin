package org.spcrey

object StatePattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val context = Context()
        context.state = AlphaState()
        context.action()
        context.state = BetaState()
        context.action()
        context.state = GammaState()
        context.action()
        context.state = object : State {
            override fun action() {
                println("DeltaState().action()")
            }
        }
        context.action()
        context.state = State {
            println("zetaState().action()")
        }
        context.action()
    }

    /**
     * Console Output:
     * AlphaState().action()
     * BetaState().action()
     * GammaState().action()
     * DeltaState().action()
     * zetaState().action()
     */

    fun interface State {
        fun action()
    }

    class AlphaState : State {
        override fun action() {
            println("AlphaState().action()")
        }
    }

    class BetaState : State {
        override fun action() {
            println("BetaState().action()")
        }
    }

    class GammaState : State {
        override fun action() {
            println("GammaState().action()")
        }
    }

    class Context(var state: State = AlphaState()) {
        fun action() {
            state.action()
        }
    }
}