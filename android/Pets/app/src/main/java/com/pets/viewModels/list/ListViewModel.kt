package com.pets.viewModels.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.pets.architecture.stores.Store
import com.pets.architecture.viewModels.ViewModel
import com.pets.domain.list.FetchList
import com.pets.domain.list.ListEvent
import com.pets.domain.list.ListReducer
import com.pets.domain.list.ListState
import com.pets.models.Pet

class ListViewModel: ViewModel<ListState, ListEvent, ListViewModel.ViewState>, androidx.lifecycle.ViewModel() {
    data class ViewState(val list: List<Pet> = emptyList(),
    val favorites: List<Pet> = emptyList(),
    val isLoading: Boolean = false)

    override var state: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    override val store: Store<ListState, ListEvent> =
        Store(MutableLiveData(ListState()), ListReducer())

    private val observer = Observer<ListState> { onStateChange(it) }

    init {
        store.state.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        store.state.removeObserver(observer)
    }

    override fun onStateChange(newState: ListState) {
        state.value =  ViewState(list = newState.list,
        isLoading = newState.isLoading,
        favorites = newState.favorites)
    }
}