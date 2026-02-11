package alex.dev.shoppingnotelistapp.data.repository.impl

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.NoteItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.NoteItem
import alex.dev.shoppingnotelistapp.data.repository.NoteItemRepository
import kotlinx.coroutines.flow.Flow

class NoteItemRepoImpl(private val dao: NoteItemDao) : NoteItemRepository {
    override suspend fun insertNoteItem(item: NoteItem) {
        dao.insertNoteItem(item)
    }

    override suspend fun deleteNoteItem(item: NoteItem) {
        dao.deleteNoteItem(item)
    }

    override fun getAllNoteItems(): Flow<List<NoteItem>> {
        return dao.getAllNoteItems()
    }

    override suspend fun getNoteItemById(id: Long): NoteItem {
        return dao.getNoteItemById(id)
    }
}