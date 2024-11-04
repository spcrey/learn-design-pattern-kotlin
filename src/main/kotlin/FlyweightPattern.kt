package org.spcrey

object FlyweightPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val flyweightFactory = FlyweightFactory()
        val flyweight1 = flyweightFactory.getFlyweight("flyweight1")
        val flyweight2 = flyweightFactory.getFlyweight("flyweight2")
        val flyweight3 = flyweightFactory.getFlyweight("flyweight1")
        println(flyweight1 == flyweight2)
        println(flyweight1 == flyweight3)
    }

    /**
     * Console Output:
     * false
     * true
     */

    data class Flyweight(val name: String)

    class FlyweightFactory {
        private val flyweightHashMap = hashMapOf<String, Flyweight>()

        fun getFlyweight(name: String): Flyweight {
            flyweightHashMap[name]?.let {
                return it
            } ?: run {
                val flyweight = Flyweight(name)
                flyweightHashMap[name] = flyweight
                return flyweight
            }
        }
    }
}