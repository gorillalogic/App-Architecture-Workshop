package com.pets.domain.favorites

import com.pets.models.Pet
import com.pets.services.Interfaces.LogAction

data class FavoritesState(
    var list: List<Pet>,
    var isLoading: Boolean
)

sealed class FavoritesEvent: LogAction {
    class fetchList: FavoritesEvent() {
        override fun description(): String {
            return "Action - Fetching Favorites List"
        }
    }

    class fetchListCompleted(val list: List<Pet>): FavoritesEvent() {
        override fun description(): String {
            return "Action - Fetching Favorites List Completed"
        }
    }

    class removeFromFavorites(val id: Int): FavoritesEvent() {
        override fun description(): String {
            return "Action - Removing from Favorites List"
        }
    }
}