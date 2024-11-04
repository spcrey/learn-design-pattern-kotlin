package org.spcrey

object FacadePattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val facade = Facade()
        facade.step()
    }

    /**
     * Console Output:
     * Facade.step1()
     * Facade.step2()
     * Facade.step3()
     * Facade.step()
     */

    class Facade {
        fun step(): Boolean {
            if (step1() && step2() && step3()) {
                println("Facade().step()")
                return true
            } else {
                return false
            }
        }

        private fun step1(): Boolean {
            println("Facade().step1()")
            return true
        }

        private fun step2(): Boolean {
            println("Facade().step2()")
            return true
        }

        private fun step3(): Boolean {
            println("Facade().step3()")
            return true
        }
    }
}