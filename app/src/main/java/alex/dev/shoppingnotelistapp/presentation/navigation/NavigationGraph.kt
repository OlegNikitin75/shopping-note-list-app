package alex.dev.shoppingnotelistapp.presentation.navigation

import alex.dev.shoppingnotelistapp.presentation.features.add_note.AddNoteScreen
import alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list.AddShoppingListScreen
import alex.dev.shoppingnotelistapp.presentation.features.note_list.NoteListScreen
import alex.dev.shoppingnotelistapp.presentation.features.shopping_list.ShoppingListScreen
import alex.dev.shoppingnotelistapp.presentation.features.shopping_list_detail.ShoppingListDetailScreen
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationGraph(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(navController = navController, startDestination = Screens.ShoppingList.screen) {
        composable(Screens.ShoppingList.screen) {
            ShoppingListScreen(
                snackbarHostState = snackbarHostState,
            )
        }
        composable(Screens.NoteList.screen) {
            NoteListScreen()
        }
        composable(Screens.AddShoppingList.screen) {
            AddShoppingListScreen(navController = navController)
        }
        composable(Screens.AddNote.screen) {
            AddNoteScreen()
        }
        composable(
            route = Screens.ShoppingListDetail.screen,
            arguments = listOf(navArgument("listId") { type = NavType.LongType })
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getLong("listId") ?: 0L
            ShoppingListDetailScreen(
                listId = listId,
                navController = navController
            )
        }
    }
}