package com.gorillalogic.architecture.pets.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.pets.domain.favorites.RemoveFromFavorites
import com.pets.viewModels.favorites.FavoritesViewModel


@Composable
fun Favorites(viewModel: FavoritesViewModel) {
    val state by viewModel.state.observeAsState(FavoritesViewModel.ViewState())
    viewModel.dispatch(com.pets.domain.favorites.FetchList)
    LazyColumn {
        items(state.list) { pet ->
            PetRow(pet = pet) {
                viewModel.dispatch(
                    RemoveFromFavorites(id = pet.id)
                )
            }
        }
    }
}