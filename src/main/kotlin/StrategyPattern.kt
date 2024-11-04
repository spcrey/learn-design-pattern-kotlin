package org.spcrey

object  StrategyPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val context = Context()
        context.strategy = AlphaStrategy()
        context.execute(1,2)
        context.strategy = BetaStrategy()
        context.execute(1,2)
        context.strategy = GammaStrategy()
        context.execute(1,2)
        context.strategy = object : Strategy() {
            override fun execute(a: Int, b: Int): Int {
                println("DeltaStrategy().execute()")
                return a / b
            }
        }
        context.execute(1,2)
        val higherOrderFunContext = HigherOrderFunContext()
        higherOrderFunContext.execute(1,2)
        higherOrderFunContext.strategy = fun(a: Int, b: Int) : Int {
            println("HigherOrderFun().execute()")
            return a + b
        }
        higherOrderFunContext.execute(1,2)
    }

    /**
     * Console Output:
     * AlphaStrategy().execute()
     * result=3
     * BetaStrategy().execute()
     * result=-1
     * GammaStrategy().execute()
     * result=2
     * DeltaStrategy().execute()
     * result=0
     * HigherOrderFun().execute()
     * result=3
     */

    abstract class Strategy {
        abstract fun execute(a: Int, b: Int): Int
    }

    class AlphaStrategy : Strategy() {
        override fun execute(a: Int, b: Int): Int {
            println("AlphaStrategy().execute()")
            return a + b
        }
    }

    class BetaStrategy : Strategy() {
        override fun execute(a: Int, b: Int): Int {
            println("BetaStrategy().execute()")
            return a - b
        }
    }

    class GammaStrategy : Strategy() {
        override fun execute(a: Int, b: Int): Int {
            println("GammaStrategy().execute()")
            return a * b
        }
    }

    class Context{
        var strategy: Strategy? = null

        fun execute(a: Int, b: Int) {
            strategy?.let {
                println("result=${it.execute(a, b)}")
            }
        }
    }

    class HigherOrderFunContext {
        var strategy: ((Int, Int) -> Int)? = null

        fun execute(a: Int, b: Int) {
            strategy?.let {
                println("result=${it(a, b)}")
            }
        }
    }
}