package alex.dev.shoppingnotelistapp.presentation.features.shopping_list_detail

import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingListDetailViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {
}