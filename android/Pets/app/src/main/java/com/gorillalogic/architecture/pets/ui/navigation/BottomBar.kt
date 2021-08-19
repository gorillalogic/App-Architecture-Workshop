package com.gorillalogic.architecture.pets.ui.navigation

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gorillalogic.architecture.pets.Screen


@Composable
fun BottomBar(tabItems: List<Screen>, navController: NavController) {
    BottomNavigation() {
        tabItems.map {
            BottomNavigationItem(
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title,
                        tint = Color.White,
                        modifier = Modifier.width(25.dp)
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
