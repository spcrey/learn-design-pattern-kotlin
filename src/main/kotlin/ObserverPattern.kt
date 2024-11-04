package org.spcrey

object ObserverPattern {
    @JvmStatic
    fun main(args: Array<String>) {
        val observer = Observer()
        Observable("alpha", observer)
        Observable("beta", observer)
        Observable("gamma", observer)
        observer.notifyObservables()
    }

    /**
     * Console Output:
     * Observer(alpha).showMessage()
     * Observer(beta).showMessage()
     * Observer(gamma).showMessage()
     */

    class Observer {
        private val observables = mutableListOf<Observable>()

        fun addObservable(observable: Observable) {
            observables.add(observable)
        }

        fun notifyObservables() {
            observables.forEach { it.showMessage() }
        }
    }

    class Observable(private val name: String, observer: Observer) {
        init {
            observer.addObservable(this)
        }

        fun showMessage() {
            println("Observer($name).showMessage()")
        }
    }
}