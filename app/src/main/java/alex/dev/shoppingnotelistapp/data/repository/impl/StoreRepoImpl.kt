package alex.dev.shoppingnotelistapp.data.repository.impl

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.StoreDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.Store
import alex.dev.shoppingnotelistapp.data.repository.StoreRepository
import kotlinx.coroutines.flow.Flow

class StoreRepoImpl(private val dao: StoreDao) : StoreRepository {
    override suspend fun insertStore(store: Store) {
        dao.insertStore(store)
    }

    override suspend fun updateStore(store: Store) {
        dao.updateStore(store)
    }

    override suspend fun deleteStore(store: Store) {
        dao.deleteStore(store)
    }

    override fun getAllStores(): Flow<List<Store>> {
        return dao.getAllStores()
    }

    override fun getStoreById(storeId: Int): Flow<Store> {
        return dao.getStoreById(storeId)
    }
}