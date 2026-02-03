package alex.dev.shoppingnotelistapp.data.repository.impl

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingListItem
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListItemRepository
import kotlinx.coroutines.flow.Flow

class ShoppingListItemRepoImpl(private val dao: ShoppingListItemDao) : ShoppingListItemRepository {
    override suspend fun insertItemToList(item: ShoppingListItem) {
        dao.insertItemToList(item)
    }

    override suspend fun updateListItem(item: ShoppingListItem) {
        dao.updateListItem(item)
    }

    override suspend fun deleteListItem(item: ShoppingListItem) {
        dao.deleteListItem(item)
    }

    override fun getAllShoppingListItems(): Flow<List<ShoppingListItem>> {
        return dao.getAllShoppingListItems()
    }

    override fun getAllShoppingListItemsById(listId: Int): Flow<List<ShoppingListItem>> {
        return dao.getAllShoppingListItemsById(listId)
    }

    override fun getShoppingListItemById(itemId: Int): Flow<List<ShoppingListItem>> {
        return dao.getShoppingListItemById(itemId)
    }
}

