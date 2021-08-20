package com.pets.domain.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Depending
import com.pets.architecture.reducers.Reducing
import com.pets.models.Pet
import com.pets.services.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ListReducer: Reducing<ListState, ListEvent>, Depending<ListReducer.Dependencies> {
    class Dependencies {
        val listService = ServiceLocator.dataService
        val favoriteService = ServiceLocator.favoriteService
        val logService = ServiceLocator.logService
    }

    override val dependencies: Dependencies
        get() = Dependencies()

    override suspend fun reduce(state: MutableLiveData<ListState>, event: ListEvent): ListEvent? =
        withContext(Dispatchers.IO) {
            val emptyList = emptyList<Pet>().toMutableList()
            when (event) {
                is FetchList -> {
                    state.value?.let {
                        state.postValue(it.copy(list = emptyList, isLoading = true))
                    }
                }

                is FetchListCompleted -> {
                    state.value?.let {
                        state.postValue(it.copy(list = event.list, isLoading = false))
                    }
                }

                is FetchFavorites -> {
                    state.value?.let {
                        state.postValue(it.copy(favorites = emptyList))
                    }

                }

                is FetchFavoriteCompleted -> {
                    state.value?.let {
                        state.postValue(it.copy(favorites = event.list))
                    }
                }

                is AddToFavorites -> {
                    val favorite = state.value?.list?.firstOrNull { it.id == event.id }
                    favorite?.let { pet ->
                        state.value?.let {
                            val favorites = it.favorites.toMutableList()
                            favorites.add(pet)
                            state.postValue(it.copy(favorites = favorites))
                        }
                    }
                }

                is RemoveFromFavorites -> {
                    state.value?.let { ls ->
                        val favorites = ls.favorites.toMutableList()
                        favorites.removeAll { it.id == event.id }
                        state.postValue(ls.copy(favorites = favorites))
                    }
                }
            }
            return@withContext sideEffect(event = event)
        }

    private suspend fun sideEffect(event: ListEvent): ListEvent? {
        dependencies.logService.log(event)
        when (event) {
            is FetchList -> {
                val list = dependencies.listService.getList()
                return FetchListCompleted(list = list)
            }

            is FetchFavorites -> {
                val list = dependencies.favoriteService.fetchList()
                return FetchFavoriteCompleted(list = list)
            }

            is AddToFavorites -> {
                dependencies.favoriteService.addToFavorites(id = event.id)
            }

            is RemoveFromFavorites -> {
                dependencies.favoriteService.removeFromFavorites(id = event.id)
            }

            else -> return null
        }
        return  null
    }
}