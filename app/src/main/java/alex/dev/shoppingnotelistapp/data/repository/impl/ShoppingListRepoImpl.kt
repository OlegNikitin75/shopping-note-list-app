package alex.dev.shoppingnotelistapp.data.repository.impl

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ItemsWithStoreAndShoppingList
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListRepoImpl @Inject constructor(private val dao: ShoppingListDao) :
    ShoppingListRepository {
    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        dao.insertShoppingList(shoppingList)
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        dao.updateShoppingList(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        dao.deleteShoppingList(shoppingList)
    }

    override fun getAllShoppingLists(): Flow<List<ShoppingList>> {
        return dao.getAllShoppingLists()
    }

    override fun getItemsWithStoreAndShoppingList(): Flow<List<ItemsWithStoreAndShoppingList>> {
        return dao.getItemsWithStoreAndShoppingList()
    }

    override fun getItemsWithStoreAndShoppingListFilteredById(listId: Int): Flow<List<ItemsWithStoreAndShoppingList>> {
        return dao.getItemsWithStoreAndShoppingListFilteredById(listId)
    }

    override fun getItemWithStoreAndShoppingListFilteredById(itemId: Int): Flow<ItemsWithStoreAndShoppingList> {
        return dao.getItemWithStoreAndShoppingListFilteredById(itemId)
    }
}