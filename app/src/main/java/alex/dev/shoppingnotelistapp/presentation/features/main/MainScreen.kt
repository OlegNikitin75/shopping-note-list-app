package alex.dev.shoppingnotelistapp.presentation.features.main

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.presentation.navigation.NavigationGraph
import alex.dev.shoppingnotelistapp.presentation.navigation.Screens
import alex.dev.shoppingnotelistapp.presentation.ui.components.BottomSheetItem
import alex.dev.shoppingnotelistapp.presentation.ui.components.bottom_nav.BottomNav
import alex.dev.shoppingnotelistapp.presentation.ui.components.theme.RobotoFlex
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentDestination = currentRoute?.destination?.route
    val showBottomBar = currentDestination in listOf(
        Screens.ShoppingList.screen,
        Screens.NoteList.screen
    )


    Scaffold(
        floatingActionButton = {
            if (showBottomBar) {
                Column(
                    modifier = Modifier.offset(y = 82.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FloatingActionButton(
                        onClick = { showBottomSheet = true },
                        modifier = Modifier
                            .size(56.dp),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        "Создать",
                        fontSize = 10.sp,
                        fontFamily = RobotoFlex,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 12.sp
                    )
                }
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        NavigationGraph(navController = navController)
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BottomSheetItem(
                    iconId = R.drawable.ic_add_shopping_list,
                    title = "Добавить список покупок",
                    onClick = {
                        showBottomSheet = false
                        navController.navigate(Screens.AddShoppingList.screen) { popUpTo(0) }
                    }
                )
                BottomSheetItem(
                    iconId = R.drawable.ic_add_note,
                    title = "Добавить заметку",
                    onClick = {
                        showBottomSheet = false
                        navController.navigate(Screens.AddNote.screen) { popUpTo(0) }
                    }
                )
            }
        }
    }
}



