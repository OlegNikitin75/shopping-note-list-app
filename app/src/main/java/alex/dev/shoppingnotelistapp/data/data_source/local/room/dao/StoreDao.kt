package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.Store
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    //Запись магазина
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store: Store)

    //Обновление магазина
    @Update
    suspend fun updateStore(store: Store)

    //Удаление магазина
    @Delete
    suspend fun deleteStore(store: Store)

    //Получение всех магазинов
    @Query("SELECT * FROM stores")
    fun getAllStores(): Flow<List<Store>>

    //Получение магазина по id
    @Query("SELECT * FROM stores WHERE store_id = :storeId")
    fun getStoreById(storeId: Int): Flow<Store>
}
