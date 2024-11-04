package org.spcrey

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object ProxyPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val realSubject: Subject = RealSubject()
        val staticProxySubject = StaticProxySubject(realSubject)
        staticProxySubject.method()
        val dynamicProxySubject = Proxy.newProxyInstance(
            realSubject.javaClass.classLoader,
            arrayOf(Subject::class.java),
            DynamicProxySubject(realSubject)
        ) as Subject
        dynamicProxySubject.method()
    }

    /**
     * Console Output:
     * StaticProxySubject().beforeMethod()
     * RealSubject().method()
     * StaticProxySubject().afterMethod()
     * DynamicProxySubject().beforeMethod()
     * RealSubject().method()
     * DynamicProxySubject().afterMethod()
     */

    interface Subject {
        fun method()
    }

    class RealSubject : Subject {
        override fun method() {
            println("RealSubject().method()")
        }
    }

    interface ProxyExecutor {
        fun beforeMethod()
        fun afterMethod()
    }

    class StaticProxySubject(private val realSubject: Subject) : Subject, ProxyExecutor {
        override fun method() {
            beforeMethod()
            realSubject.method()
            afterMethod()
        }

        override fun beforeMethod() {
            println("StaticProxySubject().beforeMethod()")
        }

        override fun afterMethod() {
            println("StaticProxySubject().afterMethod()")
        }
    }

    class DynamicProxySubject(private val realSubject: Subject) : InvocationHandler, ProxyExecutor {
        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
            beforeMethod()
            val result = if (method.parameterCount == 0) {
                method.invoke(realSubject)
            } else {
                method.invoke(realSubject, args)
            }
            afterMethod()
            return result
        }

        override fun beforeMethod() {
            println("DynamicProxySubject().beforeMethod()")
        }

        override fun afterMethod() {
            println("DynamicProxySubject().afterMethod()")
        }
    }
}