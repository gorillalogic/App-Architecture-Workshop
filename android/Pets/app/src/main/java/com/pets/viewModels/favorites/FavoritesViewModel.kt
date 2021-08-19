package com.pets.viewModels.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pets.architecture.stores.Store
import com.pets.architecture.viewModels.ViewModel
import com.pets.domain.favorites.FavoritesEvent
import com.pets.domain.favorites.FavoritesReducer
import com.pets.domain.favorites.FavoritesState
import com.pets.models.Pet

class FavoritesViewModel: ViewModel<FavoritesState, FavoritesEvent, FavoritesViewModel.ViewState>,
androidx.lifecycle.ViewModel() {
    data class ViewState(val list: List<Pet> = emptyList(),
                         val isLoading: Boolean = true)

    override var state: MutableLiveData<ViewState> = MutableLiveData()
    override val store: Store<FavoritesState, FavoritesEvent> =
        Store(MutableLiveData(FavoritesState()), reducer = FavoritesReducer())

    private val observer: Observer<FavoritesState> = Observer { onStateChange(it) }

    init {
        store.state.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        store.state.removeObserver(observer)
    }

    override fun onStateChange(newState: FavoritesState) {
        state.value = ViewState(list = newState.list, isLoading = newState.isLoading)
    }
}