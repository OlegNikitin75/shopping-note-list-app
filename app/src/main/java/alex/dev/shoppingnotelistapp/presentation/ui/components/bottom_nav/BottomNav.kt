package alex.dev.shoppingnotelistapp.presentation.ui.components.bottom_nav

import alex.dev.shoppingnotelistapp.presentation.ui.theme.RobotoFlex
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.ShoppingListItem,
        BottomNavItem.NoteItem
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            NavigationBarItem(
                selected = currentRoute == items[0].route,
                onClick = { navController.navigate(items[0].route) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary
                ),
                icon = {
                    Icon(
                        painter = painterResource(items[0].iconId),
                        contentDescription = items[0].title
                    )
                },
                label = {
                    Text(
                        text = items[0].title,
                        fontFamily = RobotoFlex,
                        fontSize = 10.sp
                    )
                }
            )
            Spacer(Modifier.weight(1f))

            NavigationBarItem(
                selected = currentRoute == items[1].route,
                onClick = { navController.navigate(items[1].route) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary
                ),
                icon = {
                    Icon(
                        painter = painterResource(items[1].iconId),
                        contentDescription = items[1].title
                    )
                },
                label = {
                    Text(
                        text = items[1].title,
                        fontFamily = RobotoFlex,
                        fontSize = 10.sp
                    )
                }
            )
        }
    }
}
