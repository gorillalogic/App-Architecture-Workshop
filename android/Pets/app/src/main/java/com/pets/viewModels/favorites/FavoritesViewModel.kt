package com.pets.viewModels.favorites

import androidx.lifecycle.MutableLiveData
import com.pets.architecture.stores.Store
import com.pets.architecture.viewModels.ViewModel
import com.pets.domain.favorites.FavoritesEvent
import com.pets.domain.favorites.FavoritesReducer
import com.pets.domain.favorites.FavoritesState
import com.pets.models.Pet

class FavoritesViewModel: ViewModel<FavoritesState, FavoritesEvent, FavoritesViewModel.ViewState> {
    data class ViewState(val list: MutableList<Pet> = emptyList<Pet>().toMutableList(),
                         val isLoading: Boolean = true)

    override var state: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    override val store: Store<FavoritesState, FavoritesEvent> =
        Store(MutableLiveData(FavoritesState()), reducer = FavoritesReducer())

    override fun onStateChange(newState: FavoritesState) {
        state.postValue(ViewState(list = newState.list, isLoading = newState.isLoading))
    }
}