package org.spcrey

object FactoryPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val alphaFactory: Factory = AlphaFactory()
        val alphaProduct: Product = alphaFactory.createProduct()
        alphaProduct.method()
        val betaFactory = BetaFactory()
        val betaProduct: Product = betaFactory.createProduct()
        betaProduct.method()
    }

    /**
     * Console Output:
     * AlphaProduct.method()
     * BetaProduct.method()
     */

    abstract class Product {
        abstract fun method()
    }

    abstract class Factory {
        abstract fun createProduct() : Product
    }

    class AlphaProduct : Product() {
        override fun method() {
            println("AlphaProduct.method()")
        }
    }

    class AlphaFactory : Factory() {
        override fun createProduct(): AlphaProduct {
            return AlphaProduct()
        }
    }

    class BetaProduct : Product() {
        override fun method() {
            println("BetaProduct.method()")
        }
    }

    class BetaFactory : Factory() {
        override fun createProduct(): BetaProduct {
            return BetaProduct()
        }
    }
}