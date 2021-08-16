package com.gorillalogic.architecture.pets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gorillalogic.architecture.pets.ui.theme.PetsTheme

sealed class Screen(val route: String, val title : String, @DrawableRes val icon : Int) {
    object PetList : Screen(route = "petlist", title = "Home", icon= R.drawable.ic_launcher_background)
    object Favorites : Screen(route = "favorites", title = "Favourites", icon= R.drawable.ic_launcher_background)
}

class MainActivity : ComponentActivity() {

    val items = listOf(Screen.PetList, Screen.Favorites)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main(tabItems = items)
                }
            }
        }
    }
}

@Composable
fun Main(tabItems: List<Screen>) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomBar(tabItems = tabItems, navController = navController)
    }) {
        MainNavigation(navController = navController)
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.PetList.route) {
        composable(Screen.PetList.route) {
            PetList()
        }
        composable(Screen.Favorites.route) {
            Favorites()
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
fun PetList() {
    Text("Pets")
}

@Composable
fun Favorites() {
    Text("Favorites")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsTheme {
        Main(tabItems = listOf(Screen.PetList, Screen.Favorites))
    }
}