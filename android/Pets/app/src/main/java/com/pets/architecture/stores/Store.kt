package com.pets.architecture.stores

import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Dispatching
import com.pets.architecture.reducers.Reducing
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Store<State, Event>(var state: MutableLiveData<State>,
                          val reducer: Reducing<State, Event>): Dispatching<Event> {

    override fun dispatch(event: Event) {
        GlobalScope.async {
            val newEvent = reducer.reduce(state, event)
            newEvent?.let { dispatch(it) }
        }
    }
}