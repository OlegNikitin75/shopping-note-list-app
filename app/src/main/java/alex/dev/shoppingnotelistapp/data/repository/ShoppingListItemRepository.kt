package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingListItem
import kotlinx.coroutines.flow.Flow

interface ShoppingListItemRepository {
    suspend fun insertItemToList(item: ShoppingListItem)
    suspend fun updateListItem(item: ShoppingListItem)
    suspend fun deleteListItem(item: ShoppingListItem)
    fun getAllShoppingListItems(): Flow<List<ShoppingListItem>>
    fun getAllShoppingListItemsById(listId: Int): Flow<List<ShoppingListItem>>
    fun getShoppingListItemById(itemId: Int): Flow<List<ShoppingListItem>>
}