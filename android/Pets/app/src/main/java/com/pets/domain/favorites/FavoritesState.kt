package com.pets.domain.favorites

import com.pets.models.Pet
import com.pets.services.Interfaces.LogAction

data class FavoritesState(
    var list: List<Pet> = emptyList<Pet>(),
    var isLoading: Boolean = false
)

sealed class FavoritesEvent: LogAction
object FetchList: FavoritesEvent() {
    override fun description(): String {
        return "Action - Fetching Favorites List"
    }
}

class FetchListCompleted(val list: List<Pet>): FavoritesEvent() {
    override fun description(): String {
        return "Action - Fetching Favorites List Completed"
    }
}

class RemoveFromFavorites(val id: Int): FavoritesEvent() {
    override fun description(): String {
        return "Action - Removing from Favorites List"
    }
}