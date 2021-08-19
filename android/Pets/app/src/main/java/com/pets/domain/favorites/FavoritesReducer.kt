package com.pets.domain.favorites

import androidx.lifecycle.LiveData
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
    = withContext(Dispatchers.IO) {
        when(event) {
            is RemoveFromFavorites -> {
                state.value?.let { fs ->
                    var favorites = fs.list.toMutableList()
                    favorites.removeAll { it.id == event.id }
                    state.postValue(fs.copy(list = favorites))
                }
            }

            is FetchListCompleted -> {
                state.value?.let { fs ->
                    state.postValue(fs.copy(list = event.list,
                    isLoading = false))
                }
            }

            is FetchList -> {
                state.value?.let {
                    state.postValue(it.copy(list = emptyList(), isLoading = true))
                }
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