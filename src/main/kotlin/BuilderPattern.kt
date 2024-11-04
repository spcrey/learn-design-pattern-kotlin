package org.spcrey

object BuilderPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val group = Group.Builder()
            .setChildA(ChildA(1, false))
            .setChildB(ChildB(3.14f, 1.73))
            .setChildA(ChildA(2, false))
            .build()
        println(group)
    }

    /**
     * Console Output:
     * Group(child1=ChildA(param1=2, param2=false), child2=ChildB(param1=3.14, param2=1.73), child3=ChildC(param1=0))
     */

    data class ChildA(val param1: Int = 0, val param2: Boolean = false)
    data class ChildB(val param1: Float = 0f, val param2: Double = 0.0)
    data class ChildC(val param1: Char = '0')

    data class Group(val child1: ChildA, val child2: ChildB, val child3: ChildC) {
        constructor(builder: Builder) : this(
            builder.childA?:ChildA(),
            builder.childB?:ChildB(),
            builder.childC?:ChildC()
        )

        class Builder {
            var childA : ChildA? = null
            var childB : ChildB? = null
            var childC : ChildC? = null

            fun setChildA(childA: ChildA): Builder {
                this.childA = childA
                return this
            }

            fun setChildB(childB: ChildB): Builder {
                this.childB = childB
                return this
            }

            fun setChildC(childC: ChildC): Builder {
                this.childC = childC
                return this
            }

            fun build(): Group {
                return Group(this)
            }
        }
    }
}