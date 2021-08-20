package com.gorillalogic.architecture.pets.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pets.domain.list.AddToFavorites
import com.pets.domain.list.FetchFavorites
import com.pets.domain.list.FetchList
import com.pets.domain.list.RemoveFromFavorites
import com.pets.viewModels.list.ListViewModel


@Composable
fun PetList(viewModel: ListViewModel) {
    val state by viewModel.state.observeAsState(ListViewModel.ViewState())
    LaunchedEffect(true) {
        viewModel.dispatch(FetchList)
    }
    if (state.isLoading) {
        Column(modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
            items(state.list) { pet ->
                val isFavorite = state.favorites.map { it.id }.contains(pet.id)
                PetCard(pet = pet, isFavorite = isFavorite) {
                    if (!isFavorite) {
                        viewModel.dispatch(event = AddToFavorites(id = pet.id))
                    } else {
                        viewModel.dispatch(event = RemoveFromFavorites(id = pet.id))
                    }
                }
            }
        }
    }
}