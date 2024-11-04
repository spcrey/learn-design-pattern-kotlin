package org.spcrey

object VisitorPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val command = Command("Old Command")
        val component = ConcreteComponent(command)
        component.execute()
        val visitor = ConcreteVisitor()
        component.accept(visitor)
        component.execute()
    }

    /**
     * Console Output:
     * execute Command(content=Old Command)
     * execute Command(content=New Command)
     */

    data class Command(val content: String)

    abstract class Component {
        abstract var command: Command
        fun execute() {
            println("execute $command")
        }
        abstract fun accept(visitor: Visitor)
    }

    class ConcreteComponent(override var command: Command) : Component() {
        override fun accept(visitor: Visitor) {
            visitor.visitConcreteComponent(this)
        }
    }

    interface Visitor {
        fun visitConcreteComponent(component: ConcreteComponent)
    }

    class ConcreteVisitor : Visitor {
        override fun visitConcreteComponent(component: ConcreteComponent) {
            component.command = Command("New Command")
        }
    }
}