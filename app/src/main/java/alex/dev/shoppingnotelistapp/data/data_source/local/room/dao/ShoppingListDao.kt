package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    //Запись списка покупок
    @Insert
    suspend fun insertShoppingList(shoppingList: ShoppingList):Long

    //Обновление списка покупок
    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList)

    //Удаление списка покупок
    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    //Получение всех списков покупок
    //Если используем Flow, то suspend не нужно!!!
    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    //Получение списка покупок по его Id
    @Query("SELECT * FROM shopping_lists WHERE shopping_list_id = :listId")
    fun getShoppingListById(listId: Long): Flow<ShoppingList?>
}

