package alex.dev.shoppingnotelistapp.presentation.features.shopping_list

import alex.dev.shoppingnotelistapp.presentation.ui.components.ShoppingListItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ShoppingListScreen(viewModel: ShoppingListViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.errorEvents.collect { message ->
            // Показать Snackbar или Dialog
//            snackbarHostState.showSnackbar(message)
        }
    }


//    viewModel.addShoppingList("Покупки на неделю")

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.errorMessage ?: "Неизвестная ошибка",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Проверьте интернет и попробуйте позже\nили перезапустите приложение",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        else -> {
            LazyColumn {
                items(state.lists) { _ ->
                    ShoppingListItem()
                }

                if (state.lists.isEmpty()) {
                    item {
                        Text(
                            text = "Список пуст",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}