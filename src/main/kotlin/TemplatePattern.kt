package org.spcrey

object TemplatePattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val concreteClassA: Template = AlphaConcreteClass()
        concreteClassA.method()
        val concreteClassB: Template = BetaConcreteClass()
        concreteClassB.method()
    }

    /**
     * Console Output:
     * AlphaConcreteClass().step1()
     * Template().step2()
     * AlphaConcreteClass().step3()
     * Template().step1()
     * BetaConcreteClass().step2()
     * Template().step3()
     */

    abstract class Template {
        fun method() {
            step1()
            step2()
            step3()
        }

        protected open fun step1() {
            println("Template().step1()")
        }

        protected open fun step2() {
            println("Template().step2()")
        }

        protected open fun step3() {
            println("Template().step3()")
        }
    }

    class AlphaConcreteClass : Template() {
        override fun step1() {
            println("AlphaConcreteClass().step1()")
        }

        override fun step3() {
            println("AlphaConcreteClass().step3()")
        }
    }

    class BetaConcreteClass : Template() {
        override fun step2() {
            println("BetaConcreteClass().step2()")
        }
    }
}