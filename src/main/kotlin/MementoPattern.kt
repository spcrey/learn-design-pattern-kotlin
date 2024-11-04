package org.spcrey

import java.util.*
import kotlin.properties.Delegates

object MementoPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val memento: Memento = StackMemento()
        val component = ConcreteComponent(memento, 0, "none")
        println(component)
        component.param1 = 2
        println(component)
        component.param2 = "3"
        println(component)
        component.restore()
        println(component)
        component.restore()
        println(component)
        component.restore()
    }

    /**
     * Console Output:
     * ConcreteComponent(param1=0, param2='none')
     * ConcreteComponent(param1=2, param2='none')
     * ConcreteComponent(param1=2, param2='3')
     * ConcreteComponent(param1=2, param2='none')
     * ConcreteComponent(param1=0, param2='none')
     * memento is empty
     */

    abstract class Memento {
        abstract fun add(backup: Backup)
        abstract fun get() : Backup?
    }

    abstract class Backup

    abstract class Component(val memento: Memento) {
        abstract fun restore()
        abstract fun save(component: Component)
    }

    class StackMemento : Memento() {
        private val stack = Stack<Backup>()

        override fun add(backup: Backup) {
            stack.push(backup)
        }

        override fun get(): Backup? {
            return if (stack.isEmpty()) {
                null
            } else {
                stack.pop()
            }
        }
    }
    class ConcreteBackup(val param1: Int, val param2: String) : Backup()

    class ConcreteComponent(memento: Memento, param1: Int, param2: String) : Component(memento) {
        private var observable = false

        var param1: Int by Delegates.observable(param1) { _, oldValue, newValue ->
            if (observable && oldValue != newValue) {
                save(ConcreteComponent(memento, oldValue, this.param2))
            }
        }

        var param2: String by Delegates.observable(param2) { _, oldValue, newValue ->
            if (observable && oldValue != newValue) {
                save(ConcreteComponent(memento, this.param1, oldValue))
            }
        }

        init {
            this.param1 = param1
            this.param2 = param2
            observable = true
        }

        override fun restore() {
            val backup = memento.get()
            backup?.let {
                observable = false
                backup as ConcreteBackup
                this.param1 = backup.param1
                this.param2 = backup.param2
                observable = true
            } ?: run {
                println("memento is empty")
            }
        }

        override fun save(component: Component) {
            component as ConcreteComponent
            memento.add(ConcreteBackup(component.param1, component.param2))
        }

        override fun toString(): String {
            return "ConcreteComponent(param1=$param1, param2='$param2')"
        }
    }
}