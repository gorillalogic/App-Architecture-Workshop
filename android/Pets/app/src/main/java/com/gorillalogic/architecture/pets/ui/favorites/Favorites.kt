package com.gorillalogic.architecture.pets.ui.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pets.domain.favorites.RemoveFromFavorites
import com.pets.viewModels.favorites.FavoritesViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun Favorites(viewModel: FavoritesViewModel) {
    val state by viewModel.state.observeAsState(FavoritesViewModel.ViewState())
            Column(modifier = Modifier
                .padding(30.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                }
                else {
                if (state.list.isEmpty()) {
                    Text(
                        text = "No favorites yet!",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
                else {
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
        }
        }
}