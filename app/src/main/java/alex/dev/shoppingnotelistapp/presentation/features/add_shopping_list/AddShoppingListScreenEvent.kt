package alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list

sealed class AddShoppingListScreenEvent {
    data class OnNameChanged(val newName: String) : AddShoppingListScreenEvent()
    data class OnPopularOptionClick(val option: String) : AddShoppingListScreenEvent()
    object OnButtonClearTextFieldClick: AddShoppingListScreenEvent()
    object OnButtonCreateShoppingListClick: AddShoppingListScreenEvent()
    data class ShowError(val message: String) : AddShoppingListScreenEvent()
}