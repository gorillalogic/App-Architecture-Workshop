package com.pets.domain.list

import com.pets.models.Pet
import com.pets.services.Interfaces.LogAction

data class ListState(
    var list: List<Pet> = emptyList(),
    var favorites: List<Pet> = emptyList(),
    var isLoading: Boolean = false
)

sealed class ListEvent: LogAction
object FetchList: ListEvent() {
    override fun description(): String {
        return "Action - Fetch List from Home"
    }
}
object FetchFavorites : ListEvent() {
    override fun description(): String {
        return "Action - Fetch Favorite List from Home"
    }
}
data class FetchListCompleted(val list: List<Pet>): ListEvent() {
    override fun description(): String {
        return "Action - Fetch List Completed from Home"
    }
}
data class FetchFavoriteCompleted(val list: List<Pet>): ListEvent() {
    override fun description(): String {
        return "Action - Fetch Favorite List Completed from Home"
    }
}
data class AddToFavorites(val id: Int): ListEvent() {
    override fun description(): String {
        return "Action - Add to Favorites from Home"
    }
}
data class RemoveFromFavorites(val id: Int): ListEvent() {
    override fun description(): String {
        return "Action - Remove Favorites from Home"
    }
}