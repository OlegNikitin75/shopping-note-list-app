package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ListItem
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
    suspend fun insertItemToList(item: ListItem)

    //Обновление элемента списка покупок
    @Update
    suspend fun updateListItem(item: ListItem)

    //Удаление элемента списка покупок
    @Delete
    suspend fun deleteListItem(item: ListItem)

    //Получение всех элементов
    @Query("SELECT * FROM items")
    fun getAllShoppingListItems(): Flow<List<ListItem>>

    //Получение всех элементов списка покупок по id выбранного списка
    @Query("SELECT * FROM items WHERE listId = :listId")
    fun getAllShoppingListItemsById(listId: Int): Flow<List<ListItem>>

    //Получение  элемента списка покупок по id выбранного элемента
    @Query("SELECT * FROM items WHERE item_id = :itemId")
    fun getShoppingListItemById(itemId: Int): Flow<List<ListItem>>
}