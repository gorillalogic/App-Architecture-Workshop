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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gorillalogic.architecture.pets.ui.theme.PetsTheme
import com.pets.domain.list.*
import com.pets.models.Pet
import com.pets.viewModels.favorites.FavoritesViewModel
import com.pets.viewModels.list.ListViewModel
import com.skydoves.landscapist.glide.GlideImage

sealed class Screen(val route: String, val title : String, @DrawableRes val icon : Int) {
    object PetList : Screen(route = "petlist", title = "Pets", icon= R.drawable.ic_launcher_background)
    object Favorites : Screen(route = "favorites", title = "Favorites", icon= R.drawable.ic_launcher_background)
}

class MainActivity : ComponentActivity() {
    private val tabItems = listOf(Screen.PetList, Screen.Favorites)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main(tabItems = tabItems)
                }
            }
        }
    }
}

@Composable
fun Main(tabItems: List<Screen>,
listViewModel: ListViewModel = viewModel(),
favoritesViewModel: FavoritesViewModel = viewModel()) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomBar(tabItems = tabItems, navController = navController)
    }) {
        MainNavigation(navController = navController,
        listViewModel = listViewModel,
        favoritesViewModel = favoritesViewModel)
    }
}

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
@Composable
fun BottomBar(tabItems: List<Screen>, navController: NavController) {
    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        tabItems.map {
            BottomNavigationItem(
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label= {
                    Text(
                        text = it.title
                    )
                },
                selected = false,
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.route)
                }
            )
        }
    }
}

@Composable
fun PetCard(pet: Pet, isFavorite: Boolean, onLikeClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            GlideImage(
                imageModel = pet.petImage,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.FillBounds,
                // shows an image with a circular revealed animation.
                circularRevealedEnabled = true
                // shows a placeholder ImageBitmap when loading.
            )

            Row() {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                        ) {
                            append(pet.name)
                        }
                    }
                )

                Button(onClick = onLikeClick) {
                    Text(text = if (isFavorite) "DisLike" else "Like")
                }
            }
        }
    }
}

@Composable
fun PetList(viewModel: ListViewModel) {
    val state by viewModel.state.observeAsState(ListViewModel.ViewState())
    viewModel.dispatch(FetchList)
    viewModel.dispatch(FetchFavorites)
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

@Composable
fun PetRow(pet: Pet, onLikeClick: () -> Unit) {
    Row(modifier = Modifier.clickable { onLikeClick() }) {
        GlideImage(
            imageModel = pet.petImage,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.FillBounds,
            // shows an image with a circular revealed animation.
            circularRevealedEnabled = true
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                ) {
                    append(pet.name)
                }
            }
        )

        Button(onClick = onLikeClick) {
            Text(text = "DisLike")
        }
    }
}

@Composable
fun Favorites(viewModel: FavoritesViewModel) {
    val state by viewModel.state.observeAsState(FavoritesViewModel.ViewState())
    viewModel.dispatch(com.pets.domain.favorites.FetchList)
    LazyColumn {
        items(state.list) { pet ->
            PetRow(pet = pet) {
                viewModel.dispatch(
                    com.pets.domain.favorites.RemoveFromFavorites(id = pet.id))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsTheme {
        Text("Preview")
    }
}