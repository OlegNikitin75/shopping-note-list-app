package alex.dev.shoppingnotelistapp.presentation.features.shopping_list_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListDetailScreen(
    listId: Long,
    navController: NavController,
    viewModel: ShoppingListDetailViewModel = hiltViewModel()
) {
//    val list by viewModel.shoppingList.collectAsStateWithLifecycle()
//
//    LaunchedEffect(listId) {
//        viewModel.loadList(listId)
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(list?.name ?: "Список") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Default.ArrowBack, "Назад")
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(16.dp)
//        ) {
//            if (list == null) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//            } else {
//                Text("Элементы списка: ${list.allItemsCount}")
//                Text("Отмечено: ${list.allSelectedItemsCount}")
//                // Здесь будет LazyColumn с товарами в будущем
//            }
//        }
//    }
}