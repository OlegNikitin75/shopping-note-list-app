package alex.dev.shoppingnotelistapp.presentation.features.shopping_list

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.presentation.ui.components.CardShoppingList
import alex.dev.shoppingnotelistapp.presentation.ui.components.UpdateAlertDialog
import alex.dev.shoppingnotelistapp.presentation.ui.theme.RobotoFlex
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun ShoppingListScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: ShoppingListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    var showUpdateDialog by remember { mutableStateOf<ShoppingList?>(null) }
    var expandedListId by remember { mutableStateOf<Long?>(null) }


    LaunchedEffect(viewModel) {
        viewModel.undoEvents.collect { event ->
            when (event) {
                is UndoEvent.ShowUndo -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = "Отменить",
                        duration = SnackbarDuration.Long
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.undoDelete(event.item)
                    }
                }

                is UndoEvent.ShowError -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.successEvents.collect { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.errorEvents.collect { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
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
                        text = "Проверьте интернет и попробуйте позже\nили перезапустите приложение",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = state.lists,
                        key = { it.id }
                    ) { item ->
                        CardShoppingList(
                            item = item,
                            isExpanded = expandedListId == item.id,
                            onExpandedChange = { shouldExpand ->
                                expandedListId = if (shouldExpand) item.id else null
                            },
                            onDelete = { viewModel.deleteShoppingList(item) },
                            onEdit = { selectedItem ->
                                showUpdateDialog = selectedItem
                            }
                        )
                    }
                }
            }
        }
    }

    if (state.lists.isEmpty() && !state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                painter = painterResource(R.drawable.empty_shopping_lists),
                contentDescription = "Пустой список",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                text = "Здесь пока нет списков!",
                textAlign = TextAlign.Center,
                fontFamily = RobotoFlex,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Нажмите на плюс,что бы создать первый",
                textAlign = TextAlign.Center,
                fontFamily = RobotoFlex,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
            )
        }
    }
    showUpdateDialog?.let { listToRename ->
        UpdateAlertDialog(
            list = listToRename,
            onDismiss = { showUpdateDialog = null },
            onSave = { newName ->
                val updated = listToRename.copy(name = newName.trim())
                viewModel.updateShoppingList(updated)
                showUpdateDialog = null
            }
        )
    }
}
