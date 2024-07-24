package socketTrench.events

interface Dispatcher {
    fun register(observer: Observer)
    fun dispatch(event: String)
}

interface Observer {
    fun handle(event: String)
}

class DefaultDispatcher : Dispatcher {
    private val observers = mutableListOf<Observer>()

    override fun register(observer: Observer) {
        observers.add(observer)
    }

    override fun dispatch(event: String) {
        for (observer in observers) {
            observer.handle(event)
        }
    }
}