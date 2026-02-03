package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingListItem
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListItemDao {
    //Запись элемента списка покупок
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToList(item: ShoppingListItem)

    //Обновление элемента списка покупок
    @Update
    suspend fun updateListItem(item: ShoppingListItem)

    //Удаление элемента списка покупок
    @Delete
    suspend fun deleteListItem(item: ShoppingListItem)

    //Получение всех элементов
    @Query("SELECT * FROM items")
    fun getAllShoppingListItems(): Flow<List<ShoppingListItem>>

    //Получение всех элементов списка покупок по id выбранного списка
    @Query("SELECT * FROM items WHERE list_id = :listId")
    fun getAllShoppingListItemsById(listId: Int): Flow<List<ShoppingListItem>>

    //Получение  элемента списка покупок по id выбранного элемента
    @Query("SELECT * FROM items WHERE item_id = :itemId")
    fun getShoppingListItemById(itemId: Int): Flow<List<ShoppingListItem>>
}