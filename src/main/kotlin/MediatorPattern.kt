package org.spcrey

object MediatorPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val mediator = ConcreteMediator()
        val alphaComponent = ConcreteComponent("alpha", mediator)
        val betaComponent = ConcreteComponent("beta", mediator)
        val gammaComponent = ConcreteComponent("gamma", mediator)
        alphaComponent.sendMessage(Message("Hello, beta, and I'm alpha"), gammaComponent)
        betaComponent.sendMessageToAll(Message("Hello, everyone, and I'm beta"))
    }

    /**
     * Console Output:
     * gamma receive "Hello, beta, and I'm alpha" from alpha
     * alpha receive "Hello, everyone, and I'm beta" from beta
     * gamma receive "Hello, everyone, and I'm beta" from beta
     */

    data class Message(val content:String)

    abstract class Component(open val mediator: Mediator) {
        abstract fun sendMessage(message: Message, receiver: Component)
        abstract fun sendMessageToAll(message: Message)
        abstract fun receiveMessage(message: Message, sender: Component)
    }

    abstract class Mediator {
        val components: MutableList<Component> = mutableListOf()
        abstract fun notify(message: Message, sender: Component, receiver: Component)
        abstract fun notifyAll(message: Message, sender: Component)
    }

    class ConcreteMediator : Mediator() {
        override fun notify(message: Message, sender: Component, receiver: Component) {
            receiver.receiveMessage(message, sender)
        }

        override fun notifyAll(message: Message, sender: Component) {
            components.forEach {
                if (it != sender) {
                    it.receiveMessage(message, sender)
                }
            }
        }
    }

    class ConcreteComponent(val name: String, override var mediator: Mediator) : Component(mediator) {
        init {
            mediator.components.add(this)
        }

        override fun sendMessage(message: Message, receiver: Component) {
            mediator.notify(message, this, receiver)
        }

        override fun sendMessageToAll(message: Message) {
            mediator.notifyAll(message, this)
        }

        override fun receiveMessage(message: Message, sender: Component) {
            sender as ConcreteComponent
            println("$name receive \"${message.content}\" from ${sender.name}")
        }
    }
}