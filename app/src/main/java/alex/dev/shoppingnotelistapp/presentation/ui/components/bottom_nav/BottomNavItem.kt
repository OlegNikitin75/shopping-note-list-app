package alex.dev.shoppingnotelistapp.presentation.ui.components.bottom_nav

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.presentation.navigation.Screens

sealed class BottomNavItem(
    val title: String,
    val iconId: Int,
    val route: String
) {
    object ShoppingListItem : BottomNavItem(
        title = "Покупки",
        iconId = R.drawable.ic_shopping_list,
        route = Screens.ShoppingList.screen
    )

    object NoteItem : BottomNavItem(
        title = "Заметки",
        iconId = R.drawable.ic_note,
        route = Screens.NoteList.screen
    )
}