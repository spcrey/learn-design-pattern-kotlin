package org.spcrey

object  ResponsibilityChainPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val alphaHandler = AlphaHandler()
        val betaHandler = BetaHandler()
        val gammaHandler = GammaHandler()
        alphaHandler.next = betaHandler
        betaHandler.next = gammaHandler
        alphaHandler.handleRequest(1)
        alphaHandler.handleRequest(10)
        alphaHandler.handleRequest(100)
        alphaHandler.handleRequest(1000)
    }

    /**
     * Console Output:
     * AlphaHandler().handleRequest()
     * BetaHandler().handleRequest()
     * GammaHandler() .handleRequest()
     */

    abstract class Handler {
        var next: Handler? = null
        abstract fun handleRequest(num: Int)
    }

    class AlphaHandler : Handler() {
        override fun handleRequest(num: Int) {
            if (num < 10) {
                println("AlphaHandler().handleRequest()")
            } else {
                next?.handleRequest(num)
            }
        }
    }

    class BetaHandler : Handler() {
        override fun handleRequest(num: Int) {
            if (num < 100) {
                println("BetaHandler().handleRequest()")
            } else {
                next?.handleRequest(num)
            }
        }
    }

    class GammaHandler : Handler() {
        override fun handleRequest(num: Int) {
            if (num < 1000) {
                println("GammaHandler().handleRequest()")
            } else {
                next?.handleRequest(num)
            }
        }
    }
}