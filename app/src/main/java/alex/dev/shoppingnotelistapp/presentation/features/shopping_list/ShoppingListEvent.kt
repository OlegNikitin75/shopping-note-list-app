package alex.dev.shoppingnotelistapp.presentation.features.shopping_list

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList

sealed class ShoppingListEvent {
    data class OnShoppingListClick(val item: ShoppingList) : ShoppingListEvent()
    object OnDropdownMenuClick : ShoppingListEvent()
    data class ShowUndo(val item: ShoppingList, val message: String) : ShoppingListEvent()
    data class OnShowUpdateDialog(val item: ShoppingList) : ShoppingListEvent()
}