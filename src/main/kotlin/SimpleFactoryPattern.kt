package org.spcrey

object SimpleFactoryPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val factory = Factory()
        val alphaProduct = factory.createProduct("Alpha")
        alphaProduct.method()
        val betaProductB = factory.createProduct("Beta")
        betaProductB.method()
        val gammaProduct = factory.createProduct("Gamma")
        gammaProduct.method()
    }

    /**
     * Console Output:
     * AlphaProduct().method()
     * BetaProduct().method()
     * GammaProduct().method()
     */

    abstract class Product {
        abstract fun method()
    }

    class AlphaProduct : Product() {
        override fun method() {
            println("AlphaProduct().method()")
        }
    }
    class BetaProduct : Product() {
        override fun method() {
            println("BetaProduct().method()")
        }
    }
    class GammaProduct : Product() {
        override fun method() {
            println("GammaProduct().method()")
        }
    }

    class Factory {
        fun createProduct(type: String) : Product {
            return when(type) {
                "Alpha" -> AlphaProduct()
                "Beta" -> BetaProduct()
                "Gamma" -> GammaProduct()
                else -> {
                    throw IllegalArgumentException("Invalid type")
                }
            }
        }
    }
}