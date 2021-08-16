package com.pets.architecture.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Dispatching
import com.pets.architecture.stores.Store

interface ViewModel<State, Event, ViewState>: Dispatching<Event>{
    var state: MutableLiveData<ViewState>
    val store: Store<State, Event>

    fun onStateChange(newState: State)

    override fun dispatch(event: Event) {
        store.dispatch(event = event)
    }
}