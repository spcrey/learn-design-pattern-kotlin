package org.spcrey

object SingletonPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val s1 = HungrySingleton.instance
        println(s1)
        val s2 = LazySingleton.getInstance("s2")
        println(s2)
        val s3 = LazySingleton.getInstance("s3")
        println(s3)
        val s4 = DoubleCheckedLockingEagerSingleton.getInstance("s4")
        println(s4)
        val s5 = DoubleCheckedLockingEagerSingleton.getInstance("s5")
        println(s5)
        println(KotlinObjectSingleton)
    }

    /**
     * Console Output:
     * HungrySingleton()
     * LazySingleton(s2)
     * LazySingleton(s2)
     * DoubleCheckedLockingEagerSingleton(s4)
     * DoubleCheckedLockingEagerSingleton(s4)
     * KotlinObjectSingleton(s5)
     */

    class HungrySingleton private constructor() {
        companion object {
            val instance by lazy {
                HungrySingleton()
            }
        }

        override fun toString(): String {
            return "HungrySingleton()"
        }
    }

    class LazySingleton private constructor(private val name: String) {
        companion object {
            private var instance: LazySingleton? = null

            @Synchronized fun getInstance(name: String): LazySingleton {
                if (instance == null) {
                    instance = LazySingleton(name)
                }
                return instance!!
            }
        }

        override fun toString(): String {
            return "LazySingleton($name)"
        }
    }

    class DoubleCheckedLockingEagerSingleton private constructor(private val name: String) {
        companion object {
            @Volatile
            private var instance: DoubleCheckedLockingEagerSingleton? = null

            fun getInstance(name: String): DoubleCheckedLockingEagerSingleton {
                if (instance == null) {
                    synchronized(DoubleCheckedLockingEagerSingleton::class.java) {
                        if (instance == null) {
                            instance = DoubleCheckedLockingEagerSingleton(name)
                        }
                    }
                }
                return instance!!
            }
        }

        override fun toString(): String {
            return "DoubleCheckedLockingEagerSingleton($name)"
        }
    }

    object KotlinObjectSingleton {
        private const val NAME = "s5"

        override fun toString(): String {
            return "KotlinObjectSingleton($NAME)"
        }
    }
}