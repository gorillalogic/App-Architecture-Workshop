package com.pets.architecture.reducers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface Reducing<State, Event> {
    suspend fun reduce(state: MutableLiveData<State>, event: Event): Event?
}