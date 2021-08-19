package com.gorillalogic.architecture.pets.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gorillalogic.architecture.pets.Screen
import com.gorillalogic.architecture.pets.ui.favorites.Favorites
import com.gorillalogic.architecture.pets.ui.list.PetList
import com.pets.domain.list.FetchFavorites
import com.pets.domain.list.FetchList
import com.pets.viewModels.favorites.FavoritesViewModel
import com.pets.viewModels.list.ListViewModel

@Composable
fun MainNavigation(navController: NavHostController,
listViewModel: ListViewModel,
favoritesViewModel: FavoritesViewModel) {
    NavHost(navController, startDestination = Screen.PetList.route) {
        composable(Screen.PetList.route) {
            PetList(listViewModel)
        }
        composable(Screen.Favorites.route) {
            Favorites(favoritesViewModel)
        }
    }
}