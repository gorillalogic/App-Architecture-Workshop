package com.pets.architecture.viewModels

import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Dispatching
import com.pets.architecture.stores.Store

interface ViewModel<State, Event>: Dispatching<Event> {
    interface ViewState
    var state: MutableLiveData<ViewState>
    val store: Store<State, Event>

    fun onStateChange(newState: State)

    override fun dispatch(event: Event) {
        store.dispatch(event = event)
    }
}