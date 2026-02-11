package alex.dev.shoppingnotelistapp.presentation.navigation

sealed class Screens(val screen: String) {
    data object ShoppingList : Screens("shopping_list")
    data object NoteList : Screens("note_list")
    data object AddShoppingList : Screens("add_shopping_list")
    data object ShoppingListDetail : Screens("shopping_list/{listId}") {
        fun createRoute(listId: Long) = "shopping_list/$listId"
    }

    data object AddNote : Screens("add_note")
}