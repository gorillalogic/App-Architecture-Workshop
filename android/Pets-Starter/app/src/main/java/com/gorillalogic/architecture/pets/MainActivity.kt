package com.gorillalogic.architecture.pets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gorillalogic.architecture.pets.ui.list.PetCard
import com.gorillalogic.architecture.pets.ui.navigation.BottomBar
import com.gorillalogic.architecture.pets.ui.navigation.MainNavigation
import com.gorillalogic.architecture.pets.ui.theme.PetsTheme
import com.pets.domain.list.*
import com.pets.models.Pet
import com.pets.viewModels.favorites.FavoritesViewModel
import com.pets.viewModels.list.ListViewModel
import com.skydoves.landscapist.glide.GlideImage

sealed class Screen(val route: String, val title : String, @DrawableRes val icon : Int) {
    object PetList : Screen(route = "petlist", title = "Pets", icon= R.drawable.pawprint)
    object Favorites : Screen(route = "favorites", title = "Favorites", icon= R.drawable.heart_fill)
}

class MainActivity : ComponentActivity() {
    private val tabItems = listOf(Screen.PetList, Screen.Favorites)
    val listViewModel = ListViewModel()
    val favoritesViewModel = FavoritesViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main(tabItems = tabItems,
                        listViewModel = listViewModel,
                        favoritesViewModel = favoritesViewModel)
                }
            }
        }
    }
}

@Composable
fun Main(tabItems: List<Screen>,
listViewModel: ListViewModel,
favoritesViewModel: FavoritesViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
        BottomBar(tabItems = tabItems, navController = navController)
        }
    ) {
        MainNavigation(navController = navController,
        listViewModel = listViewModel,
        favoritesViewModel = favoritesViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsTheme {
        Text("Preview")
    }
}