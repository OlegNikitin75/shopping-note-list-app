package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ItemsWithStoreAndShoppingList
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import kotlinx.coroutines.flow.Flow

interface
ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingList)
    suspend fun updateShoppingList(shoppingList: ShoppingList)
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
    fun getAllShoppingLists(): Flow<List<ShoppingList>>
    fun getItemsWithStoreAndShoppingList(): Flow<List<ItemsWithStoreAndShoppingList>>
    fun getItemsWithStoreAndShoppingListFilteredById(listId: Int): Flow<List<ItemsWithStoreAndShoppingList>>
    fun getItemWithStoreAndShoppingListFilteredById(itemId: Int): Flow<ItemsWithStoreAndShoppingList>
}