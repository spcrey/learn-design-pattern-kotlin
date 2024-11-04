package org.spcrey

object IteratorPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val container: Container<Int> = ConcreteContainer()
        intArrayOf(1,2,3,4,5).forEach { container.add(it) }
        val iterator: Iterator<Int> = container.iterator()
        while (iterator.hasNext()) {
            println(iterator.next())
        }
    }

    /**
     * Console Output:
     * 1
     * 2
     * 3
     * 4
     * 5
     */

    interface Iterator<T> {
        fun next(): T
        fun hasNext(): Boolean
    }

    abstract class Container<T> {
        abstract fun add(element: T)
        abstract val size: Int
        abstract fun getItem(index: Int): T
        abstract fun iterator(): Iterator<T>
    }

    class ConcreteIterator<T>(private val container: Container<T>): Iterator<T> {
        private var index = 0

        override fun next(): T {
            return container.getItem(index++)
        }

        override fun hasNext(): Boolean {
            return index < container.size
        }
    }

    class ConcreteContainer<T>() : Container<T>() {
        private val list = mutableListOf<T>()

        override fun add(element: T) {
            list.add(element)
        }

        override val size: Int
            get() = list.size

        override fun getItem(index: Int): T {
            return list[index]
        }

        override fun iterator(): Iterator<T> {
            return ConcreteIterator(this)
        }
    }
}