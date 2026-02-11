package alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import alex.dev.shoppingnotelistapp.errors.AppError
import alex.dev.shoppingnotelistapp.utils.AppResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {
    private val _formState = MutableStateFlow(AddShoppingListFormState())
    val formState: StateFlow<AddShoppingListFormState> = _formState.asStateFlow()
    private val _navigation = Channel<NavigationEvent>(Channel.BUFFERED)
    val navigationEvents = _navigation.receiveAsFlow()
    fun onEvent(event: AddShoppingListScreenEvent) {
        when (event) {
            is AddShoppingListScreenEvent.OnPopularOptionClick -> {
                _formState.update {
                    it.copy(
                        name = event.option,
                        errorMessage = null
                    )
                }
            }

            is AddShoppingListScreenEvent.OnNameChanged -> {
                _formState.update {
                    it.copy(
                        name = event.newName,
                        errorMessage = null
                    )
                }
            }

            is AddShoppingListScreenEvent.OnButtonClearTextFieldClick -> {
                _formState.update {
                    it.copy(
                        name = "",
                        errorMessage = null
                    )
                }
            }

            is AddShoppingListScreenEvent.OnButtonCreateShoppingListClick -> {
                createShoppingList()
            }

            else -> {}
        }
    }

    private fun createShoppingList() {
        viewModelScope.launch {
            val current = _formState.value
            _formState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            val newList = ShoppingList(
                name = current.name.trim()
            )

            when (val result = repository.insertShoppingList(newList)) {
                is AppResult.Success -> {
                    val insertedId = result.data
                    _formState.update {
                        it.copy(
                            isLoading = false,
                            successListId = insertedId,
                            errorMessage = null
                        )
                    }
                    _navigation.send(NavigationEvent.ToShoppingListDetail(insertedId))
                }

                is AppResult.Failure<*> -> {
                    val errorMessage = when (val error = result.error) {
                        is AppError.ConstraintViolation -> {
                            result.error.userMessage
                        }

                        is AppError.DatabaseError -> {
                            result.error.userMessage
                        }

                        is AppError.Unknown ->
                            result.error.userMessage
                    }

                    _formState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = errorMessage
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}

data class AddShoppingListFormState(
    val name: String = "Новый список",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successListId: Long? = null
)