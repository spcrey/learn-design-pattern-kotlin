package org.spcrey

object AdapterPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val adaptee = Adaptee()
        val adapter = Adapter(adaptee)
        println(adapter.transform())
    }

    /**
     * Console Output:
     * Adapter().handleContent()
     * Transformed Adaptee().method()
     */

    interface Transformer {
        fun transform() : String
    }

    class Adaptee {
        fun method(): String {
            return "Adaptee().method()"
        }
    }

    class Adapter(private val adaptee: Adaptee) : Transformer {
        private fun handleContent(content: String): String {
            println("Adapter().handleContent()")
            return "Transformed $content"
        }

        override fun transform(): String {
            return handleContent(adaptee.method());
        }
    }
}