package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.utils.AppResult
import kotlinx.coroutines.flow.Flow

interface
ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingList): AppResult<Long>
    suspend fun updateShoppingList(shoppingList: ShoppingList): AppResult<Unit>
    suspend fun deleteShoppingList(shoppingList: ShoppingList): AppResult<Unit>
    fun getAllShoppingLists(): Flow<List<ShoppingList>>
    fun getShoppingListById(listId: Long): Flow<ShoppingList?>
}