package com.gorillalogic.architecture.pets.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.pets.domain.list.AddToFavorites
import com.pets.domain.list.FetchFavorites
import com.pets.domain.list.FetchList
import com.pets.domain.list.RemoveFromFavorites
import com.pets.viewModels.list.ListViewModel


@Composable
fun PetList(viewModel: ListViewModel) {
    val state by viewModel.state.observeAsState(ListViewModel.ViewState())
    LazyColumn {
        items(state.list) { pet ->
            PetCard(pet = pet, isFavorite = state.favorites.contains(pet)) {
                if (!state.favorites.contains(pet)) {
                    viewModel.dispatch(event = AddToFavorites(id = pet.id))
                } else {
                    viewModel.dispatch(event = RemoveFromFavorites(id = pet.id))
                }
            }
        }
    }
}