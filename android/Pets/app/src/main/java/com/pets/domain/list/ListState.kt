package com.pets.domain.list

import com.pets.models.Pet

data class ListState(
    val list: List<Pet>,
    val favorites: List<Pet>,
    val isLoading: Boolean
)

sealed class ListEvent {
    class fetchList: ListEvent()
    class fetchFavorites: ListEvent()
    class fetchListCompleted(val list: List<Pet>): ListEvent()
    class fetchFavoriteCompleted(val list: List<Pet>): ListEvent()
    class addToFavorites(id: Int)
    class removeFromFavorites(id: Int)
}