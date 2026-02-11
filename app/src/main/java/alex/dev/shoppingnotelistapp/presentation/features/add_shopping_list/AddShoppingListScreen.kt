package alex.dev.shoppingnotelistapp.presentation.features.add_shopping_list

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.presentation.navigation.Screens
import alex.dev.shoppingnotelistapp.presentation.ui.components.AppTextField
import alex.dev.shoppingnotelistapp.presentation.ui.components.PopularOptionsChips
import alex.dev.shoppingnotelistapp.presentation.ui.components.buttons.AppPrimaryButton
import alex.dev.shoppingnotelistapp.presentation.utils.OptionsChips
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShoppingListScreen(
    navController: NavController,
    viewModel: AddShoppingListViewModel = hiltViewModel()
) {
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val navigationEvent by viewModel.navigationEvents.collectAsStateWithLifecycle(initialValue = null)
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(navigationEvent) {
        when (val event = navigationEvent) {
            is NavigationEvent.ToShoppingListDetail -> {
                navController.navigate(Screens.ShoppingListDetail.createRoute(event.listId)) {
                    popUpTo(Screens.AddShoppingList.screen) { inclusive = true }
                }
            }

            null -> {}
        }
    }
    LaunchedEffect(formState.errorMessage) {
        formState.errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Long,
                withDismissAction = true
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text("Создать новый список") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_left),
                            contentDescription = "Назад",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AppTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    label = "Новый список",
                    value = formState.name,
                    onValueChange = { viewModel.onEvent(AddShoppingListScreenEvent.OnNameChanged(it)) },
                    iconId = R.drawable.ic_circle_close,
                    onIconClick = { viewModel.onEvent(AddShoppingListScreenEvent.OnButtonClearTextFieldClick) },
//                    isError = formState.errorMessage != null,
                )

                AppPrimaryButton(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    label = "Создать",
                    onClick = {
                        viewModel.onEvent(AddShoppingListScreenEvent.OnButtonCreateShoppingListClick)
                    },
                    isLoading = formState.isLoading,
                    isEnabled = formState.name.trim().isNotEmpty() && !formState.isLoading
                )
                PopularOptionsChips(
                    options = OptionsChips.options,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    onOptionSelected = { option ->
                        viewModel.onEvent(AddShoppingListScreenEvent.OnPopularOptionClick(option))
                    }
                )
            }
        }
    }
}

