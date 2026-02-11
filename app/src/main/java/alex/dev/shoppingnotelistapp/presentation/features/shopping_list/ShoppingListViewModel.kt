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
    // Состояние экрана
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
                        errorMessage = "Не удалось загрузить списки: ${throwable.message ?: "ошибка"}"
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = ShoppingListUiState(isLoading = true)
            )

    // События для UI (ошибки и успехи)
    private val _errorEvents = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val errorEvents: SharedFlow<String> = _errorEvents.asSharedFlow()
    private val _successEvents = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val successEvents: SharedFlow<String> = _successEvents.asSharedFlow()

    // Undo для удаления
    private val _undoEvents = MutableSharedFlow<UndoEvent>(extraBufferCapacity = 1)
    val undoEvents: SharedFlow<UndoEvent> = _undoEvents.asSharedFlow()
    fun deleteShoppingList(item: ShoppingList) {
        viewModelScope.launch {
            try {
                repository.deleteShoppingList(item)
                // Успех — показываем Snackbar с undo
                _undoEvents.emit(
                    UndoEvent.ShowUndo(
                        item = item,
                        message = "Список «${item.name}» удалён"
                    )
                )
            } catch (e: Exception) {
                _errorEvents.emit("Не удалось удалить список: ${e.localizedMessage ?: "ошибка"}")
            }
        }
    }

    fun undoDelete(item: ShoppingList) {
        viewModelScope.launch {
            try {
                repository.insertShoppingList(item)
                _successEvents.emit("Список восстановлен")
            } catch (e: Exception) {
                _errorEvents.emit("Не удалось восстановить список")
            }
        }
    }

    fun updateShoppingList(updatedList: ShoppingList) {
        viewModelScope.launch {
            val trimmedName = updatedList.name.trim()

            if (trimmedName.isBlank()) {
                _errorEvents.emit("Название списка не может быть пустым")
                return@launch
            }
            try {
                // Обновляем с уже очищенным именем
                repository.updateShoppingList(updatedList.copy(name = trimmedName))
                _successEvents.emit("Список обновлён")
            } catch (e: Exception) {
                _errorEvents.emit("Ошибка обновления: ${e.localizedMessage ?: "неизвестная ошибка"}")
            }
        }
    }
}

sealed class UndoEvent {
    data class ShowUndo(val item: ShoppingList, val message: String) : UndoEvent()
    data class ShowError(val message: String) : UndoEvent()
}

data class ShoppingListUiState(
    val isLoading: Boolean = true,
    val lists: List<ShoppingList> = emptyList(),
    val errorMessage: String? = null
)