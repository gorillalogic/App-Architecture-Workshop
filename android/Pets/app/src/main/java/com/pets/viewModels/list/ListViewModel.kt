package com.pets.viewModels.list

import androidx.lifecycle.MutableLiveData
import com.pets.architecture.stores.Store
import com.pets.architecture.viewModels.ViewModel
import com.pets.domain.list.ListEvent
import com.pets.domain.list.ListReducer
import com.pets.domain.list.ListState
import com.pets.models.Pet

class ListViewModel: ViewModel<ListState, ListEvent, ListViewModel.ViewState> {
    data class ViewState(val list: MutableList<Pet> = emptyList<Pet>().toMutableList(),
    val favorites: MutableList<Pet> = emptyList<Pet>().toMutableList(),
    val isLoading: Boolean = false)

    override var state: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    override val store: Store<ListState, ListEvent> =
        Store(MutableLiveData(ListState()), ListReducer())

    override fun onStateChange(newState: ListState) {
        state.postValue(ViewState(list = newState.list,
        isLoading = newState.isLoading,
        favorites = newState.favorites))
    }
}