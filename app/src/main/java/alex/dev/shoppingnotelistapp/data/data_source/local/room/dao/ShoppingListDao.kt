package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ListItem
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.Store
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    //Запись списка покупок
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    //Обновление списка покупок
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateShoppingList(name: String)

    //Удаление списка покупок
    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    //Получение всех списков покупок
    //Если используем Flow, то suspend не нужно!!!
    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    //Получение всех элементов с информацией о списке покупок,
    // к которому они относятся
    // и данными о магазине, где их нужно купить
    @Query(
        """
        SELECT * FROM items AS I INNER JOIN shopping_lists AS S
        ON I.listId = S.shopping_list_id INNER JOIN stores AS ST
        ON I.storeId = ST.store_id
        """
    )
    fun getItemsWithStoreAndShoppingList(): Flow<List<ItemsWithStoreAndShoppingList>>

    //Получение все элементов из указанного списка покупок
    //с данными о самом списке покупок
    //и данными о магазинах, где эти товары нужно купить
    @Query(
        """
        SELECT * FROM items AS I INNER JOIN shopping_lists AS S
        ON I.listId = S.shopping_list_id INNER JOIN stores AS ST
        ON I.storeId = ST.store_id WHERE S.shopping_list_id = :listId
        """
    )
    fun getItemsWithStoreAndShoppingListFilteredById(listId: Int): Flow<List<ItemsWithStoreAndShoppingList>>

    //Получение одного элемента с информацией о списке покупок,
    // к которому он относятся
    // и данными о магазине, где его нужно купить
    @Query(
        """
        SELECT * FROM items AS I INNER JOIN shopping_lists AS S
        ON I.listId = S.shopping_list_id INNER JOIN stores AS ST
        ON I.storeId = ST.store_id WHERE I.item_id=:itemId
        """
    )
    fun getItemWithStoreAndShoppingListFilteredById(itemId: Int): Flow<ItemsWithStoreAndShoppingList>
}

data class ItemsWithStoreAndShoppingList(
    @Embedded val item: ListItem,
    @Embedded val shoppingList: ShoppingList,
    @Embedded val store: Store
)