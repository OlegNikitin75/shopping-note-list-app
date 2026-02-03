package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun insertStore(store: Store)
    suspend fun updateStore(store: Store)
    suspend fun deleteStore(store: Store)
    fun getAllStores(): Flow<List<Store>>
    fun getStoreById(storeId: Int): Flow<Store>
}