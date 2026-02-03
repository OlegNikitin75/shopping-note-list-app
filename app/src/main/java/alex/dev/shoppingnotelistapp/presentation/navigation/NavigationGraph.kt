package alex.dev.shoppingnotelistapp.presentation.navigation

import alex.dev.shoppingnotelistapp.presentation.features.add_note.AddNoteScreen
import alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list.AddShoppingListScreen
import alex.dev.shoppingnotelistapp.presentation.features.note_list.NoteListScreen
import alex.dev.shoppingnotelistapp.presentation.features.shopping_list.ShoppingListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ShoppingList.screen) {
        composable(Screens.ShoppingList.screen) {
            ShoppingListScreen()
        }
        composable(Screens.NoteList.screen) {
            NoteListScreen()
        }
        composable(Screens.AddShoppingList.screen) {
            AddShoppingListScreen()
        }
        composable(Screens.AddNote.screen) {
            AddNoteScreen()
        }
    }
}