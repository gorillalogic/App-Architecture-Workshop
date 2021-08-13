package com.pets.architecture.common

interface Dispatching<Event> {
    fun dispatch(event: Event)
}