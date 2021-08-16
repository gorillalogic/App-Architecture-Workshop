package com.pets.domain.favorites

import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Depending
import com.pets.architecture.reducers.Reducing
import com.pets.domain.list.ListEvent
import com.pets.models.Pet
import com.pets.services.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesReducer: Reducing<FavoritesState, FavoritesEvent>,
    Depending<FavoritesReducer.Dependencies> {
    class Dependencies {
        var service = ServiceLocator.favoriteService
        var logService = ServiceLocator.logService
    }

    override val dependencies: Dependencies
        get() = Dependencies()

    override suspend fun reduce(state: MutableLiveData<FavoritesState>, event: FavoritesEvent): FavoritesEvent?
    = withContext(Dispatchers.Main) {
        when(event) {
            is RemoveFromFavorites -> {
                val favorites = state.value?.list ?: emptyList<Pet>().toMutableList()
                favorites.removeAll { it.id == event.id }
                state.postValue(FavoritesState(list = favorites,
                    isLoading = state.value?.isLoading ?: false))
            }

            is FetchListCompleted -> {
                state.postValue(FavoritesState(list = event.list.toMutableList(),
                    isLoading = false))
            }

            is FetchList -> {
                state.postValue(FavoritesState(list = emptyList<Pet>().toMutableList(),
                isLoading = true))
            }
        }
        return@withContext sideEffect(event = event)
    }

    private suspend fun sideEffect(event: FavoritesEvent): FavoritesEvent? {
        dependencies.logService.log(event)
        when (event) {
            is FetchList -> {
                val list = dependencies.service.fetchList()
                return FetchListCompleted(list = list)
            }

            is RemoveFromFavorites -> {
                dependencies.service.removeFromFavorites(id = event.id)
            }

            else -> return null
        }

        return null
    }
}