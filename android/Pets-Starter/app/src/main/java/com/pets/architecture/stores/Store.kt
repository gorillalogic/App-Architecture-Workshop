package com.pets.architecture.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Dispatching
import com.pets.architecture.reducers.Reducing
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Store<State, Event>(var state: MutableLiveData<State>,
                          val reducer: Reducing<State, Event>): Dispatching<Event> {

}