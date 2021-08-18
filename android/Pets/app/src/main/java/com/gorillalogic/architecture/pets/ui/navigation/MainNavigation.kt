package com.gorillalogic.architecture.pets.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gorillalogic.architecture.pets.Favorites
import com.gorillalogic.architecture.pets.PetList
import com.gorillalogic.architecture.pets.Screen
import com.pets.viewModels.favorites.FavoritesViewModel
import com.pets.viewModels.list.ListViewModel

@Composable
fun MainNavigation(navController: NavHostController,
                   listViewModel: ListViewModel,
                   favoritesViewModel: FavoritesViewModel
) {
    NavHost(navController, startDestination = Screen.PetList.route) {
        composable(Screen.PetList.route) {
            PetList(listViewModel)
        }
        composable(Screen.Favorites.route) {
            Favorites(favoritesViewModel)
        }
    }
}