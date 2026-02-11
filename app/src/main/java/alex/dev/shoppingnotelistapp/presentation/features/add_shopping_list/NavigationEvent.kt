package alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list

sealed class NavigationEvent {
    data class ToShoppingListDetail(val listId: Long) : NavigationEvent()
}