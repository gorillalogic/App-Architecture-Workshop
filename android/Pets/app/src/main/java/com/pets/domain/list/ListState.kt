package com.pets.domain.list

import com.pets.models.Pet

data class ListState(
    val list: MutableList<Pet> = emptyList<Pet>().toMutableList(),
    val favorites: MutableList<Pet> = emptyList<Pet>().toMutableList(),
    val isLoading: Boolean = false
)

sealed class ListEvent
object FetchList: ListEvent()
object FetchFavorites : ListEvent()
data class FetchListCompleted(val list: List<Pet>): ListEvent()
data class FetchFavoriteCompleted(val list: List<Pet>): ListEvent()
data class AddToFavorites(val id: Int): ListEvent()
data class RemoveFromFavorites(val id: Int): ListEvent()