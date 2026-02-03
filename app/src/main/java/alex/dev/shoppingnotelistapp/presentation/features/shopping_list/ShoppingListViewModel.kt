package alex.dev.shoppingnotelistapp.presentation.features.shopping_list

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {
    private val _errorEvents = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val errorEvents: SharedFlow<String> = _errorEvents.asSharedFlow()
    private val _successEvents = MutableSharedFlow<String>()
    val successEvents = _successEvents.asSharedFlow()
    val uiState: StateFlow<ShoppingListUiState> =
        repository.getAllShoppingLists()
            .map { lists ->
                ShoppingListUiState(
                    isLoading = false,
                    lists = lists,
                    errorMessage = null
                )
            }
            .catch { throwable ->
                emit(
                    ShoppingListUiState(
                        isLoading = false,
                        errorMessage = "Не удалось загрузить: ${throwable.message ?: "ошибка"}"
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = ShoppingListUiState(isLoading = true)
            )

    fun addShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            val name = shoppingList.name.trim()
            if (name.isBlank()) {
                _errorEvents.emit("Название списка не может быть пустым")
                return@launch
            }
            try {
                repository.insertShoppingList(
                    ShoppingList(
                        name = shoppingList.name,
                        createdAt = System.currentTimeMillis(),
                        allItemsCount = 0,
                        allSelectedItemsCount = 0,
                    )
                )
                _successEvents.emit("Список «${name}» добавлен")
            } catch (e: Exception) {
                _errorEvents.emit("Не удалось добавить список покупок: ${e.localizedMessage ?: "ошибка"}")
            }
        }
    }

    fun deleteShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            try {
                repository.deleteShoppingList(shoppingList)
            } catch (e: Exception) {
                _errorEvents.emit("Не удалось удалить: ${e.localizedMessage ?: "ошибка"}")
            }
        }
    }
}

data class ShoppingListUiState(
    val isLoading: Boolean = true,
    val lists: List<ShoppingList> = emptyList(),
    val errorMessage: String? = null
)
