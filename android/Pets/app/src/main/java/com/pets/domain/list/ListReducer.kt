package com.pets.domain.list

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
    }

    override val dependencies: Dependencies
        get() = ListReducer.Dependencies()

    override suspend fun reduce(state: MutableLiveData<ListState>, event: ListEvent): ListEvent? =
        withContext(Dispatchers.Main) {
            val emptyList = emptyList<Pet>().toMutableList()
            when (event) {
                is FetchList -> {
                    state.postValue(
                        ListState(
                            list = emptyList,
                            isLoading = true,
                            favorites = state.value?.favorites ?: emptyList
                        )
                    )
                }

                is FetchListCompleted -> {
                    state.postValue(
                        ListState(
                            list = event.list.toMutableList(),
                            isLoading = false,
                            favorites = state.value?.favorites ?: emptyList
                        )
                    )
                }

                is FetchFavorites -> {
                    state.postValue(
                        ListState(
                            list = state.value?.list ?: emptyList,
                            isLoading = state.value?.isLoading ?: false,
                            favorites = emptyList
                        )
                    )
                }

                is FetchFavoriteCompleted -> {
                    state.postValue(
                        ListState(
                            list = state.value?.list ?: emptyList,
                            isLoading = state.value?.isLoading ?: false,
                            favorites = event.list.toMutableList()
                        )
                    )
                }

                is AddToFavorites -> {
                    val favorite = state.value?.list?.firstOrNull { it.id == event.id }
                    favorite?.let {
                        val newFavorites = state.value?.favorites ?: emptyList
                        newFavorites.add(it)
                        state.postValue(
                            ListState(
                                list = state.value?.list ?: emptyList,
                                isLoading = state.value?.isLoading ?: false,
                                favorites = newFavorites
                            )
                        )
                    }
                }

                is RemoveFromFavorites -> {
                    val favorites = state.value?.favorites ?: emptyList
                    favorites.removeAll { it.id == event.id }
                    state.postValue(
                        ListState(
                            list = state.value?.list ?: emptyList,
                            isLoading = state.value?.isLoading ?: false,
                            favorites = favorites
                        )
                    )
                }
            }

            return@withContext sideEffect(event = event)
        }

    private suspend fun sideEffect(event: ListEvent): ListEvent? {
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