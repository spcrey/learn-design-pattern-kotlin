package org.spcrey

object PrototypePattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = mutableListOf("A", "B", "C", "D")
        val cloneable = ConcreteCloneable(1, list)
        val cloner = cloneable.clone()
        cloneable.num = 2
        cloneable.list[3] = "E"
        println(cloneable)
        println(cloner)
    }

    /**
     * Console Output:
     * ConcreteCloneable(num=2, list=[A, B, C, E])
     * ConcreteCloneable(num=1, list=[A, B, C, D])
     */

    interface Cloneable {
        fun clone(): Cloneable
    }

    data class ConcreteCloneable(var num: Int, val list: MutableList<String>) : Cloneable {
        override fun clone(): ConcreteCloneable {
            return ConcreteCloneable(num, list.map { it }.toMutableList())
        }
    }
}