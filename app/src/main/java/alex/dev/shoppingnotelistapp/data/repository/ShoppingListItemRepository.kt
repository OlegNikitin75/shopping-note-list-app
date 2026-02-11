package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingListItem
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface ShoppingListItemRepository {
    //Запись элемента списка покупок
    suspend fun insertItemToList(item: ShoppingListItem)

    //Обновление элемента списка покупок
    suspend fun updateListItem(item: ShoppingListItem)

    //Удаление элемента списка покупок
    suspend fun deleteListItem(item: ShoppingListItem)

    //Получение всех элементов списка покупок по id выбранного списка
    fun getAllShoppingListItems(listId: Long): Flow<List<ShoppingListItem>>

    //Получение  элемента списка покупок по id выбранного элемента
    suspend fun getShoppingListItemById(itemId: Long): ShoppingListItem

    //Получение списка покупок по id выбранного списка
    suspend fun getShoppingListById(listId: Long): ShoppingList

    //Обновление списка покупок
    suspend fun updateShoppingList(shoppingList: ShoppingList)
}