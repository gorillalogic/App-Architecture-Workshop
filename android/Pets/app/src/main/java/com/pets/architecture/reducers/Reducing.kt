package com.pets.architecture.reducers

import androidx.lifecycle.MutableLiveData

interface Reducing<State, Event> {
    fun reduce(state: MutableLiveData<State>, event: Event): Event?
}